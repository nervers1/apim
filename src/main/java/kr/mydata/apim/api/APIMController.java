package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import kr.mydata.apim.mapper.APIMapper;
import kr.mydata.apim.service.APIService;
import kr.mydata.apim.vo.APIEntity;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.irp.ReqIRP001;
import kr.mydata.apim.vo.irp.ReqIRP002;
import kr.mydata.apim.vo.irp.ReqIRP003;
import kr.mydata.apim.vo.irp.ReqIRP004;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class APIMController {

  private final APIService service;
  private final ObjectMapper mapper = new ObjectMapper();
  final JdbcTemplate jdbcTemplate;

  public APIMController(APIService service, JdbcTemplate jdbcTemplate) {
    this.service = service;
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("/v1/apis")
  public ResponseEntity apis(@ModelAttribute ReqCmn001 req,
                             @RequestHeader(value = "x-api-id") String api_id) {

    String org_cd = req.getOrg_code();
    String sql = "SELECT * FROM tb_test_data WHERE 1 = 1";
    String condition = api_id != null ? " and api_id = " + api_id : "";
    String orgConditions = org_cd != null ? " and org_cd = '" + org_cd + "'" : "";
    sql += condition;
    sql += orgConditions;

    try {
      List res = new ArrayList();
      List<APIEntity> result = jdbcTemplate.query(sql, new APIMapper());
      for (APIEntity api : result) {
        Gson gsonObj = new Gson();
        String jsonStr = api.getRes_data();
        ResCmn001 resCmn001 = gsonObj.fromJson(jsonStr, ResCmn001.class);
        res.add(api.getRes_data());
      }
      // Jackson JSONPObject
      List<JSONPObject> jsonb = result.stream().map(APIEntity::getRes_data).map((strJson) -> new JSONPObject(strJson, JSONPObject.class)).collect(Collectors.toList());
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  // #OAuth 2.0
  // <base path> / oauth / 2.0 / [authorize | token | revoke]
  // # 업권별 정보제공 API
  // <base path> / <version> / <industry> / <resource>
  // # 지원 API
  // <base path> / <version> / <industry> / <resource>

// 1)버전 : /v1 ...
// 2)업권별 정보제공 API : /bank, /card, /invest, /insu, /efin, /capital ...

  /**
   * 개인형 IRP 계좌 목록 조회 (은행, 금투, 보험 공통)
   *
   * @param api_id
   * @param req
   * @return ResIRP001
   */
  @GetMapping(value = "/v1/irps", produces = "application/json; charset=UTF-8")
  public ResponseEntity irps(@RequestHeader(value = "x-api-id") String api_id,
                             ReqIRP001 req) {
    log.info("api_id : {}", api_id);
    log.info("org_code : {}", req.getOrg_code());
    // TODO ...
    return new ResponseEntity(HttpStatus.OK);
  }

  /**
   * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 보험 공통)
   *
   * @param api_id
   * @param req
   * @return ResIRP002
   */
  @PostMapping(value = "/v1/irps/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpBasic(
      @RequestHeader(value = "x-api-id") String api_id,
      ReqIRP002 req) {
    log.info("api_id : {}", api_id);
    log.info("account_no : {}", req.getAccount_num());

    // TODO ...
    return new ResponseEntity(HttpStatus.OK);

  }


  /**
   * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 보험 공통)
   *
   * @param api_id
   * @param req
   * @return ResIRP003
   */
  @PostMapping(value = "/v1/irps/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpDetail(
      @RequestHeader(value = "x-api-id") String api_id,
      ReqIRP003 req) {
    log.info("api_id : {}", api_id);
    log.info("account_no : {}", req.getAccount_num());

    // TODO ...
    return new ResponseEntity(HttpStatus.OK);

  }


  /**
   * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 보험 공통)
   *
   * @param api_id
   * @param req
   * @return ResIRP004
   */
  @PostMapping(value = "/v1/irps/transactions", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpTransactions(
      @RequestHeader(value = "x-api-id") String api_id, ReqIRP004 req) {
    log.info("api_id : {}", api_id);
    log.info("org_code : {}", req.getOrg_code());
    log.info("account_no : {}", req.getAccount_num());

    // TODO ...
    return new ResponseEntity(HttpStatus.OK);

  }


}

