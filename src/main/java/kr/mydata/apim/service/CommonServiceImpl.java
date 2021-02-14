package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import kr.mydata.apim.mapper.APIMapper;
import kr.mydata.apim.mapper.ResCmn001Mapper;
import kr.mydata.apim.vo.APIEntity;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CommonServiceImpl implements CommonService {
  private final JdbcTemplate jdbcTemplate;

  public CommonServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * API 목록 조회 (공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  @Override
  public ResCmn001 listAPI(ReqCmn001 req, String api_id, String own_org_cd) {
    String org_cd = req.getOrg_code();
    String sql = "SELECT * FROM tb_test_data WHERE 1 = 1";
    String condition = api_id != null ? " and api_id = " + api_id : "";
    String orgConditions = org_cd != null ? " and org_cd = '" + org_cd + "'" : "";
    sql += condition;
    sql += orgConditions;
    ResCmn001 result = jdbcTemplate.queryForObject(sql, new ResCmn001Mapper());
    //List res = new ArrayList();
    /*for (APIEntity api : result) {
      Gson gsonObj = new Gson();
      String jsonStr = api.getRes_data();
      ResCmn001 resCmn001 = gsonObj.fromJson(jsonStr, ResCmn001.class);
      res.add(api.getRes_data());
    }
    // Jackson JSONPObject
    List<JSONPObject> jsonb = result.stream().map(APIEntity::getRes_data).map((strJson) -> new JSONPObject(strJson, JSONPObject.class)).collect(Collectors.toList());*/

    return result;
  }

  /**
   * 전송요구 내역 조회(공통)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  @Override
  public ResCmn002 listConsents(ReqCmn002 req, String api_id, String own_org_cd) {
    return null;
  }
}
