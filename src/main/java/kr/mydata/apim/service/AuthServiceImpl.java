package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.vo.auth.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {
  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;

  public AuthServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public ResAuth001 authorize(ReqAuth001 req, String api_id, String own_org_cd) {
    return null;
  }

  @Override
  public ResAuth002 token(ReqAuth002 req, String api_id, String own_org_cd) {
    return null;
  }

  @Override
  public ResAuth003 token(ReqAuth003 req, String api_id, String own_org_cd) {
    return null;
  }

  @Override
  public void revoke(ReqAuth004 req, String api_id, String own_org_cd) {

  }
}
