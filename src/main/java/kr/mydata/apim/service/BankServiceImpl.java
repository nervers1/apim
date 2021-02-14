package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.mapper.bank.ResBank001Mapper;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class BankServiceImpl implements BankService {

  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;

  public BankServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 은행업권 : 계좌 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws JsonProcessingException {

    Map<String, String> search_timestamp = new HashMap<>();
    search_timestamp.put("search_timestamp", req.getSearch_timestamp());
    String strTimeStamp = mapper.writeValueAsString(search_timestamp);
    //String condition = req.getSearch_timestamp() != null ? " and res_data @> '" + strTimeStamp + "'::jsonb" : "";
    //String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + condition;

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ?";
    ResBank001 res = jdbcTemplate.queryForObject(sql,new ResBank001Mapper(), Integer.valueOf(api_id), own_org_cd);

    log.info("res --> {}", res);

    /*mapper.registerModule(new JavaTimeModule());
    ResBank001 resBank001 = mapper.readValue(res, ResBank001.class);
    log.info("resBank001 --> {}", resBank001);*/

    return res;
  }

  /**
   * 은행업권 - 수신계좌 기본정보 조회(은행)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
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

  /**
   * 은행업권 - 수신계좌 추가정보 조회(은행)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
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

  /**
   * 은행업권 - 수신계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
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

  /**
   * 은행업권 - 투자상품계좌 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
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

  /**
   * 은행업권 - 투자상품계좌 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
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

  /**
   * 은행업권 - 투자상품계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResBank007 investTransactions(ReqBank007 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 은행업권 - 대출상품계좌 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResBank008 loanBasic(ReqBank008 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 은행업권 - 대출상품계좌 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResBank009 loanDetail(ReqBank009 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 은행업권 - 대출상품계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResBank010 loanTransactions(ReqBank010 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }
}
