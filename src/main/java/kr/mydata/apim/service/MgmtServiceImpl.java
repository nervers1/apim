package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.vo.mgmts.*;
import kr.mydata.apim.vo.mgmts.ReqMgmts001;
import kr.mydata.apim.vo.mgmts.ResMgmts001;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MgmtServiceImpl implements MgmtService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public MgmtServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResMgmts001 supportToken(ReqMgmts001 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts001.class);
    }

    @Override
    public ResMgmts002 orgs(ReqMgmts002 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts002.class);
    }

    @Override
    public ResMgmts003 services(ReqMgmts003 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts003.class);
    }

    @Override
    public ResMgmts004 statistics(ReqMgmts004 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getMydata_org_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts004.class);
    }

    @Override
    public ResMgmts005 caCredentials(ReqMgmts005 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts005.class);
    }

    @Override
    public ResMgmts101 companyToken(ReqMgmts101 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd);
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts101.class);
    }

    @Override
    public ResMgmts102 status(ReqMgmts102 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts102.class);
    }

    @Override
    public ResMgmts103 consents(ReqMgmts103 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts103.class);
    }

    @Override
    public ResMgmts104 reqStatistics(ReqMgmts104 req, String api_id, String own_org_cd) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getMydata_org_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResMgmts104.class);
    }
}
