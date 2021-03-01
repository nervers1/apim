package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.card.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

        ResCard004 resCard004 = mapper.readValue(res, ResCard004.class);

        List<ResCard004Sub> bill_list = resCard004.getBill_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(bill_list);

        int page = Util.getPage(req.getNext_page());
        bill_list = bill_list.stream()
                .skip(page)
                .limit(Integer.valueOf(req.getLimit()))
                .collect(Collectors.toList());
        resCard004.setBill_list(bill_list);
        resCard004.setNext_page(Util.getNextPage(Integer.valueOf(resCard004.getBill_cnt()), page, Integer.valueOf(req.getLimit())));
        resCard004.setBill_cnt(String.valueOf(bill_list.size()));

        return resCard004;
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

        ResCard005 resCard005 = mapper.readValue(res, ResCard005.class);

        List<ResCard005Sub> bill_detail_list = resCard005.getBill_detail_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(bill_detail_list);

        int page = Util.getPage(req.getNext_page());
        bill_detail_list = bill_detail_list.stream()
                .skip(page)
                .limit(Integer.valueOf(req.getLimit()))
                .collect(Collectors.toList());
        resCard005.setBill_detail_list(bill_detail_list);
        resCard005.setNext_page(Util.getNextPage(Integer.valueOf(resCard005.getBill_detail_cnt()), page, Integer.valueOf(req.getLimit())));
        resCard005.setBill_detail_cnt(String.valueOf(bill_detail_list.size()));

        return resCard005;
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

        ResCard007 resCard007 = mapper.readValue(res, ResCard007.class);

        Collections.sort(resCard007.getApproved_list());

        return resCard007;
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

        ResCard008 resCard008 = mapper.readValue(res, ResCard008.class);

        Collections.sort(resCard008.getApproved_list());

        return resCard008;
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
