package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.vo.irp.*;
import kr.mydata.apim.vo.loan.ResLoan001;
import kr.mydata.apim.vo.loan.ResLoan002;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class IRPServiceImpl implements IRPService {
  public IRPServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;
  /**
   * 개인형 IRP - 계좌 목록 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param inderstry
   * @return
   */
  @Override
  public ResIRP001 listAccount(ReqIRP001 req, String api_id, String own_org_cd, String inderstry) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResIRP001.class);
  }

  /**
   * 개인형 IRP - 계좌 기본정보 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param inderstry
   * @return
   */
  @Override
  public ResIRP002 irpBasic(ReqIRP002 req, String api_id, String own_org_cd, String inderstry) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResIRP002.class);
  }

  /**
   * 개인형 IRP - 계좌 추가정보 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param inderstry
   * @return
   */
  @Override
  public ResIRP003 irpDetail(ReqIRP003 req, String api_id, String own_org_cd, String inderstry) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResIRP003.class);
  }

  /**
   * 개인형 IRP - 계좌 거래내역 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param inderstry
   * @return
   */
  @Override
  public ResIRP004 irpTransactions(ReqIRP004 req, String api_id, String own_org_cd, String inderstry) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResIRP004.class);
  }
}
