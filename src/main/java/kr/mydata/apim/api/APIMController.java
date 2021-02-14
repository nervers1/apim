package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.service.APIService;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import kr.mydata.apim.vo.irp.ReqIRP001;
import kr.mydata.apim.vo.irp.ReqIRP002;
import kr.mydata.apim.vo.irp.ReqIRP003;
import kr.mydata.apim.vo.irp.ReqIRP004;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class APIMController {

  private final APIService service;
  private final CommonService common;

  public APIMController(APIService service, CommonService common) {
    this.service = service;
    this.common = common;
  }

  private final ObjectMapper mapper = new ObjectMapper();


  @GetMapping("/v1/apis")
  public ResponseEntity apis(@RequestHeader(value = "x-api-id") String api_id,
                             @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                             @ModelAttribute ReqCmn001 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCmn001 resCmn001 = common.listAPI(req, api_id, own_org_cd);
      return new ResponseEntity(resCmn001, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/v1/consents")
  public ResponseEntity listConsents(@RequestHeader(value = "x-api-id") String api_id,
                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                @ModelAttribute ReqCmn002 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCmn002 resCmn002 = common.listConsents(req, api_id, own_org_cd);
      return new ResponseEntity(resCmn002, HttpStatus.OK);
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
  public ResponseEntity irps(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
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
      @RequestHeader(value = "x-own-org-cd") String own_org_cd,
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
      @RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
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
      @RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP004 req) {
    log.info("api_id : {}", api_id);
    log.info("org_code : {}", req.getOrg_code());
    log.info("account_no : {}", req.getAccount_num());

    // TODO ...
    return new ResponseEntity(HttpStatus.OK);

  }


}

