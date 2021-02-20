package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.invest.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InvestServiceImpl implements InvestService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public InvestServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 금융투자 업권 : 계좌 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInvest001 listAccount(ReqInvest001 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInvest001.class);
    }

    /**
     * 금융투자 업권 : 계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInvest002 listBasic(ReqInvest002 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInvest002.class);
    }

    /**
     * 금융투자 업권 : 계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInvest003 listTransactions(ReqInvest003 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ResInvest003 resVo = mapper.readValue(res, ResInvest003.class);
        List<ResInvest003Sub> trans_list = resVo.getTrans_list();

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
     * 금융투자 업권 : 계좌 상품정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public ResInvest004 listProducts(ReqInvest004 req, String api_id, String own_org_cd) throws JsonProcessingException {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResInvest004.class);
    }
}
