package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BankServiceImpl implements BankService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public BankServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 은행업권 : 계좌(수신계좌/대출상품/투자상품) 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank001 resVo = mapper.readValue(res, ResBank001.class);
        List<ResBank001Sub> account_list = resVo.getAccount_list();

        Collections.sort(account_list, new Comparator<ResBank001Sub>() {
            @Override
            public int compare(ResBank001Sub o1, ResBank001Sub o2) {
                if(o1.getAccount_type().equals(o2.getAccount_type())) {
                    // 같은 계좌유형이면 계좌번호 오름차순 정렬
                    return o1.getAccount_num().compareTo(o2.getAccount_num());
                } else {
                    // 계좌유형 오름차순 정렬
                    return o1.getAccount_type().compareTo(o2.getAccount_type());
                }
            }
        });

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        account_list = account_list.stream()
                                   .skip(page)
                                   .limit(Integer.valueOf(req.getLimit()) + 1)
                                   .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(account_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setAccount_cnt((StringUtils.hasLength(resVo.getNext_page()))
                              ? String.valueOf(account_list.size() - 1) : String.valueOf(account_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setAccount_list((StringUtils.hasLength(resVo.getNext_page()))
                               ? account_list.subList(0, account_list.size() - 1) : account_list);

        return resVo;
    }

    /**
     * 은행업권 - 수신계좌 기본정보 조회(은행)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank002 inqBasicInfo(ReqBank002 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and ast_id = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), req.getAccount_num(), req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank002.class);
    }

    /**
     * 은행업권 - 수신계좌 추가정보 조회(은행)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank003 addtionalInfo(ReqBank003 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank003.class);
    }

    /**
     * 은행업권 - 수신계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank004 listTransactions(ReqBank004 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank004 resVo = mapper.readValue(res, ResBank004.class);
        List<ResBank004Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
                               .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        trans_list = trans_list.stream()
                               .skip(page)
                               .limit(Integer.valueOf(req.getLimit()) + 1)
                               .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(trans_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setTrans_cnt((StringUtils.hasLength(resVo.getNext_page()))
                           ? String.valueOf(trans_list.size() - 1) : String.valueOf(trans_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setTrans_list((StringUtils.hasLength(resVo.getNext_page()))
                            ? trans_list.subList(0, trans_list.size() - 1) : trans_list);

        return resVo;
    }

    /**
     * 은행업권 - 투자상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank005 investBasic(ReqBank005 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank005.class);
    }

    /**
     * 은행업권 - 투자상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank006 investDetail(ReqBank006 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank006.class);
    }

    /**
     * 은행업권 - 투자상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank007 investTransactions(ReqBank007 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank007 resVo = mapper.readValue(res, ResBank007.class);
        List<ResBank007Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
                               .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        trans_list = trans_list.stream()
                               .skip(page)
                               .limit(Integer.valueOf(req.getLimit()) + 1)
                               .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(trans_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setTrans_cnt((StringUtils.hasLength(resVo.getNext_page()))
                           ? String.valueOf(trans_list.size() - 1) : String.valueOf(trans_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setTrans_list((StringUtils.hasLength(resVo.getNext_page()))
                            ? trans_list.subList(0, trans_list.size() - 1) : trans_list);

        return resVo;
    }

    /**
     * 은행업권 - 대출상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank008 loanBasic(ReqBank008 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank008.class);
    }

    /**
     * 은행업권 - 대출상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank009 loanDetail(ReqBank009 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResBank009.class);
    }

    /**
     * 은행업권 - 대출상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResBank010 loanTransactions(ReqBank010 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank010 resVo = mapper.readValue(res, ResBank010.class);
        List<ResBank010Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
                               .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        trans_list = trans_list.stream()
                               .skip(page)
                               .limit(Integer.valueOf(req.getLimit()) + 1)
                               .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(trans_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setTrans_cnt((StringUtils.hasLength(resVo.getNext_page()))
                           ? String.valueOf(trans_list.size() - 1) : String.valueOf(trans_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setTrans_list((StringUtils.hasLength(resVo.getNext_page()))
                            ? trans_list.subList(0, trans_list.size() - 1) : trans_list);

        return resVo;
    }
}
