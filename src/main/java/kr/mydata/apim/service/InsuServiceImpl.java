package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.insu.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class InsuServiceImpl implements InsuService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public InsuServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 보험업권 - 보험 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu001 listInsu(ReqInsu001 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu001.class);
    }

    /**
     * 보험업권 - 보험 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu002 insuBasic(ReqInsu002 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu002.class);
    }

    /**
     * 보험업권 - 보험 특약정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu003 insuContracts(ReqInsu003 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu003.class);
    }

    /**
     * 보험업권 - 자동차보험 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu004 insuCar(ReqInsu004 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu004.class);
    }

    /**
     * 보험업권 - 보험 납입정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu005 insuPayment(ReqInsu005 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu005.class);
    }

    /**
     * 보험업권 - 보험 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu006 insuTransactions(ReqInsu006 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResInsu006 resVo = mapper.readValue(res, ResInsu006.class);
        List<ResInsu006Sub> trans_list = resVo.getTrans_list();

        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(req.getLimit() * (page - 1))
                .limit(req.getLimit())
                .collect(Collectors.toList());
        resVo.setTrans_list(trans_list);
        resVo.setNext_page(Util.getNextPage(resVo.getTrans_cnt(), page, req.getLimit()));
        return resVo;
    }

    /**
     * 보험업권 - 자동차보험 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu007 insuCarTransactions(ReqInsu007 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getInsu_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResInsu007 resVo = mapper.readValue(res, ResInsu007.class);
        List<ResInsu007Sub> trans_list = resVo.getTrans_list();

        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(req.getLimit() * (page - 1))
                .limit(req.getLimit())
                .collect(Collectors.toList());
        resVo.setTrans_list(trans_list);
        resVo.setNext_page(Util.getNextPage(resVo.getTrans_cnt(), page, req.getLimit()));
        return resVo;
    }

    /**
     * 보험업권 - 대출상품 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu008 insuLoans(ReqInsu008 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu008.class);
    }

    /**
     * 보험업권 - 대출상품 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu009 insuLoansBasic(ReqInsu009 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu009.class);
    }

    /**
     * 보험업권 - 대출상품 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu010 insuLoansDetail(ReqInsu010 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInsu010.class);
    }

    /**
     * 보험업권 - 대출상품 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInsu011 insuLoansTransactions(ReqInsu011 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResInsu011 resVo = mapper.readValue(res, ResInsu011.class);
        List<ResInsu011Sub> trans_list = resVo.getTrans_list();

        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(req.getLimit() * (page - 1))
                .limit(req.getLimit())
                .collect(Collectors.toList());
        resVo.setTrans_list(trans_list);
        resVo.setNext_page(Util.getNextPage(resVo.getTrans_cnt(), page, req.getLimit()));
        return resVo;
    }
}
