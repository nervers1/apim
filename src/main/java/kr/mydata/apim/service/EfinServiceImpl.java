package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.efin.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class EfinServiceImpl implements EfinService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public EfinServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 전자금융 - 전자지급수단 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResEfin001 accounts(ReqEfin001 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
                req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResEfin001.class);
    }

    /**
     * 전자금융 - 전자지급수단 잔액정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResEfin002 balance(ReqEfin002 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
                req.getOrg_code(), req.getSub_key());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResEfin002.class);
    }

    /**
     * 전자금융 - 전자지급수단 자동충전정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResEfin003 charge(ReqEfin003 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
                req.getOrg_code(), req.getSub_key());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResEfin003.class);
    }

    /**
     * 전자금융 - 선불 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResEfin004 prepaidTransactions(ReqEfin004 req, String api_id, String own_org_cd)
            throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
                req.getOrg_code(), req.getSub_key());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResEfin004 ResEfin004 = mapper.readValue(res, ResEfin004.class);
        List<ResEfin004Sub> trans_list = ResEfin004.getTrans_list();

        Collections.sort(trans_list);

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(page)
                .limit(Integer.valueOf(req.getLimit()))
                .collect(Collectors.toList());
        ResEfin004.setTrans_list(trans_list);
        ResEfin004.setNext_page(Util.getNextPage(Integer.valueOf(ResEfin004.getTrans_cnt()), page, Integer.valueOf(req.getLimit())));
        ResEfin004.setTrans_cnt(String.valueOf(trans_list.size()));
        return ResEfin004;
    }

    /**
     * 전자금융 - 결제내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResEfin005 transactions(ReqEfin005 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getSub_key());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResEfin005 resVo = mapper.readValue(res, ResEfin005.class);
        List<ResEfin005Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                                     .filter(obj -> obj.getTrans_dtime().compareTo(req.getFrom_dtime()) >= 0)
                                     .filter(obj -> obj.getTrans_dtime().compareTo(req.getTo_dtime()) <= 0)
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
