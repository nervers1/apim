package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class BankServiceImpl implements BankService {

  private final ObjectMapper mapper = new ObjectMapper();
  final JdbcTemplate jdbcTemplate;
  public BankServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws JsonProcessingException {

    Map<String, String> search_timestamp = new HashMap<>();
    search_timestamp.put("search_timestamp", req.getSearch_timestamp());
    String strTimeStamp = mapper.writeValueAsString(search_timestamp);
    String condition = req.getSearch_timestamp() != null ? " and res_data @> '" + strTimeStamp + "'::jsonb" : "";

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + condition;

    PGobject res = jdbcTemplate.queryForObject(sql, PGobject.class);
    log.info("res --> {}", res);

    mapper.registerModule(new JavaTimeModule());
    ResBank001 resBank001 = mapper.readValue(res.toString(), ResBank001.class);

    return resBank001;
  }

  @Override
  public ResBank002 inqBasicInfo(ReqBank002 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and ast_id = '" + req.getAccount_num() + "'";
    // 은행
    String res = jdbcTemplate.queryForObject(sql, String.class);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    ResBank002 resBank002 = mapper.readValue(res, ResBank002.class);

    return resBank002;
  }

  @Override
  public ResBank003 addtionalInfo(ReqBank003 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and ast_id = '" + req.getAccount_num() + "'";
    // 은행
    String res = jdbcTemplate.queryForObject(sql, String.class);
    // String to JSON
    mapper.registerModule(new JavaTimeModule());
    ResBank003 resBank003 = mapper.readValue(res, ResBank003.class);

    return resBank003;
  }

  @Override
  public ResBank004 listTransactions(ReqBank004 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and ast_id = '" + req.getAccount_num() + "'";
    // 은행

    //PGobject res = jdbcTemplate.queryForObject(sql, PGobject.class);
    ResBank004 resBank004 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      ResBank004 entity = new ResBank004();
      entity.setRsp_code(rs.getString("rsp_code"));
      entity.setRsp_msg(rs.getString("rsp_msg"));
      entity.setNext_page(rs.getObject("next_page", ResBank004Sub.class));
      entity.setTrans_cnt(rs.getInt("trans_cnt"));
      entity.setTrans_list((List<ResBank004Sub>)rs.getObject("trans_list", List.class));
      return entity;
    });

    // to JSON
    /*mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResBank004 resBank004 = mapper.readValue(res.toString(), ResBank004.class);*/
    return resBank004;
  }

  @Override
  public ResBank005 investBasic(ReqBank005 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and ast_id = '" + req.getAccount_num() + "'";
    // 은행
    String res = jdbcTemplate.queryForObject(sql, String.class);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResBank005 resBank005 = mapper.readValue(res, ResBank005.class);
    return resBank005;
  }

  @Override
  public ResBank006 investDetail(ReqBank006 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and ast_id = '" + req.getAccount_num() + "'";
    // 은행
    String res = jdbcTemplate.queryForObject(sql, String.class);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResBank006 resBank006 = mapper.readValue(res, ResBank006.class);
    return resBank006;
  }

  @Override
  public ResBank007 investTransactions(ReqBank007 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }
}
