package kr.mydata.apim.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank001 resBank001 = mapper.readValue(res, ResBank001.class);
        log.info("resBank001 --> {}", resBank001);

        return resBank001;
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

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResBank002 resBank002 = mapper.readValue(res, ResBank002.class);
        log.info("resBank002 --> {}", resBank002);

        return resBank002;
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

        ResBank003 resBank003 = mapper.readValue(res, ResBank003.class);
        log.info("resBank003 --> {}", resBank003);

        return resBank003;
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

        String sql = "SELECT res_data " +
                "       FROM tb_test_data" +
                "      WHERE api_id = ? " +
                "        and own_org_cd = ? " +
                "        and org_cd = ? " +
                "        and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());

        mapper.registerModule(new JavaTimeModule());

    /*ResBank004 resBank004 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      ResBank004 entity = new ResBank004();
      entity.setRsp_code(rs.getString("rsp_code"));
      entity.setRsp_msg(rs.getString("rsp_msg"));
      entity.setNext_page(rs.getString("next_page"));
      entity.setTrans_cnt(rs.getInt("trans_cnt"));
      entity.setTrans_list((List<ResBank004Sub>)rs.getObject("trans_list", List.class));
      return entity;
    }, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());*/
        ResBank004 resVo = mapper.readValue(res, ResBank004.class);
        List<ResBank004Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(page)
                .limit(Integer.valueOf(req.getLimit()))
                .collect(Collectors.toList());
        resVo.setTrans_list(trans_list);
        resVo.setNext_page(Util.getNextPage(Integer.valueOf(resVo.getTrans_cnt()), page, Integer.valueOf(req.getLimit())));
        resVo.setTrans_cnt(String.valueOf(trans_list.size()));
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
        // to JSON
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
        // to JSON
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
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResBank007 resBank007 = mapper.readValue(res, ResBank007.class);
        List<ResBank007Sub> trans_list = resBank007.getTrans_list();

        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(req.getLimit() * (page - 1))
                .limit(req.getLimit())
                .collect(Collectors.toList());
        resBank007.setTrans_list(trans_list);
        resBank007.setNext_page(Util.getNextPage(resBank007.getTrans_cnt(), page, req.getLimit()));
        return resBank007;
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
        // to JSON
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
        // to JSON
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
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResBank010 resBank010 = mapper.readValue(res, ResBank010.class);
        List<ResBank010Sub> trans_list = resBank010.getTrans_list();

        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
                .skip(req.getLimit() * (page - 1))
                .limit(req.getLimit())
                .collect(Collectors.toList());
        resBank010.setTrans_list(trans_list);
        resBank010.setNext_page(Util.getNextPage(resBank010.getTrans_cnt(), page, req.getLimit()));
        return resBank010;
    }
}
