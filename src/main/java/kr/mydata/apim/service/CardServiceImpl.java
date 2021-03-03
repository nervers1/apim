package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.card.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@Service
public class CardServiceImpl implements CardService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public CardServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 카드업권 : 카드 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard001 listCard(ReqCard001 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResCard001.class);
    }

    /**
     * 카드업권 : 카드 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    @Override
    public ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd, String card_id) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), card_id);

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResCard002.class);
    }

    /**
     * 카드업권 : 포인트 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard003 cardPoint(ReqCard003 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResCard003.class);
    }

    /**
     * 카드업권 : 청구 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard004 cardBills(ReqCard004 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard004 resVo = mapper.readValue(res, ResCard004.class);

        List<ResCard004Sub> bill_list = resVo.getBill_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(bill_list);

        // 거래일자 조건 처리
        bill_list = bill_list.stream()
                               .filter(obj -> obj.getCharge_month().compareTo(req.getFrom_month()) >= 0)
                               .filter(obj -> obj.getCharge_month().compareTo(req.getTo_month()) <= 0)
                               .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        bill_list = bill_list.stream()
                               .skip(page)
                               .limit(Integer.valueOf(req.getLimit()) + 1)
                               .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(bill_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setBill_cnt((StringUtils.hasLength(resVo.getNext_page()))
                           ? String.valueOf(bill_list.size() - 1) : String.valueOf(bill_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setBill_list((StringUtils.hasLength(resVo.getNext_page()))
                            ? bill_list.subList(0, bill_list.size() - 1) : bill_list);

        return resVo;
    }


    /**
     * 카드업권 : 청구 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard005 cardBillsDetail(ReqCard005 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard005 resVo = mapper.readValue(res, ResCard005.class);

        List<ResCard005Sub> bill_detail_list = resVo.getBill_detail_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(bill_detail_list);

        // 거래일자 조건 처리
        bill_detail_list = bill_detail_list.stream()
                             .filter(obj -> obj.getPaid_dtime().substring(0, 6).compareTo(req.getCharge_month()) == 0)
                             .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        bill_detail_list = bill_detail_list.stream()
                             .skip(page)
                             .limit(Integer.valueOf(req.getLimit()) + 1)
                             .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(bill_detail_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setBill_detail_cnt((StringUtils.hasLength(resVo.getNext_page()))
                          ? String.valueOf(bill_detail_list.size() - 1) : String.valueOf(bill_detail_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setBill_detail_list((StringUtils.hasLength(resVo.getNext_page()))
                           ? bill_detail_list.subList(0, bill_detail_list.size() - 1) : bill_detail_list);

        return resVo;
    }

    /**
     * 카드업권 : 결제정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard006 cardPayment(ReqCard006 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard006 resCard006 = mapper.readValue(res, ResCard006.class);

        Collections.sort(resCard006.getPay_list());

        return resCard006;
    }

    /**
     * 카드업권 : 국내 승인내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    @Override
    public ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd, String card_id) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), card_id);

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard007 resVo = mapper.readValue(res, ResCard007.class);

        List<ResCard007Sub> approved_list = resVo.getApproved_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(approved_list);

        // 거래일자 조건 처리
        approved_list = approved_list.stream()
                             .filter(obj -> obj.getApproved_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                             .filter(obj -> obj.getApproved_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
                             .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        approved_list = approved_list.stream()
                             .skip(page)
                             .limit(Integer.valueOf(req.getLimit()) + 1)
                             .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(approved_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setApproved_cnt((StringUtils.hasLength(resVo.getNext_page()))
                          ? String.valueOf(approved_list.size() - 1) : String.valueOf(approved_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setApproved_list((StringUtils.hasLength(resVo.getNext_page()))
                           ? approved_list.subList(0, approved_list.size() - 1) : approved_list);

        return resVo;
    }

    /**
     * 카드업권 : 해외 승인내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    @Override
    public ResCard008 cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd, String card_id) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), card_id);

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard008 resVo = mapper.readValue(res, ResCard008.class);

        List<ResCard008Sub> approved_list = resVo.getApproved_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(approved_list);

        // 거래일자 조건 처리
        approved_list = approved_list.stream()
                                     .filter(obj -> obj.getApproved_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                                     .filter(obj -> obj.getApproved_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
                                     .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        approved_list = approved_list.stream()
                                     .skip(page)
                                     .limit(Integer.valueOf(req.getLimit()) + 1)
                                     .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(approved_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setApproved_cnt((StringUtils.hasLength(resVo.getNext_page()))
                              ? String.valueOf(approved_list.size() - 1) : String.valueOf(approved_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setApproved_list((StringUtils.hasLength(resVo.getNext_page()))
                               ? approved_list.subList(0, approved_list.size() - 1) : approved_list);

        return resVo;
    }

    /**
     * 카드업권 : 대출상품 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard009 cardLoans(ReqCard009 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResCard009.class);
    }

    /**
     * 카드업권 : 리볼빙 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard010 cardLoansRevolving(ReqCard010 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard010 resCard010 = mapper.readValue(res, ResCard010.class);

        Collections.sort(resCard010.getRevolving_list());

        return resCard010;
    }

    /**
     * 카드업권 : 단기대출 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard011 cardLoansShortTerm(ReqCard011 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard011 resCard011 = mapper.readValue(res, ResCard011.class);

        Collections.sort(resCard011.getShort_term_list());

        return resCard011;
    }

    /**
     * 카드업권 : 장기대출 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResCard012 cardLoansLongTerm(ReqCard012 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResCard012 resCard012 = mapper.readValue(res, ResCard012.class);

        Collections.sort(resCard012.getLong_term_list());

        return resCard012;
    }
}
