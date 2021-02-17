package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.service.APIService;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import kr.mydata.apim.vo.invest.ReqInvest001;
import kr.mydata.apim.vo.invest.ResInvest001;
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

  /**
   * 공통 API - API 목록 조회 (공통)
   *
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/v1/{inderstry}/apis", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResCmn001> listAccount(@RequestHeader(value = "x-api-id") String api_id,
                                               @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                               @PathVariable String inderstry,
                                               ReqCmn001 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCmn001 resCmn001 = common.listAPI(req, api_id, own_org_cd, inderstry);
      return new ResponseEntity<>(resCmn001, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/v1/{inderstry}/consents")
  public ResponseEntity<ResCmn002> listConsents(@RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @PathVariable String inderstry,
                                                @ModelAttribute ReqCmn002 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCmn002 resCmn002 = common.listConsents(req, api_id, own_org_cd, inderstry);
      return new ResponseEntity<>(resCmn002, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}

