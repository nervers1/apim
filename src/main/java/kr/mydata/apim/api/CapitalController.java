package kr.mydata.apim.api;

import kr.mydata.apim.service.CapitalService;
import kr.mydata.apim.vo.loan.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping(value = "/v1/capital")
@RestController
public class CapitalController {

  private final CapitalService service;

  public CapitalController(CapitalService service) {
    this.service = service;
  }


  /**
   * 할부금융 업권 - 계좌 목록 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResLoan001> loans(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                          ReqLoan001 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResLoan001 res = service.loans(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


  }

  /**
   * 할부금융 업권 - 대출상품계좌 기본정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/loans/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResLoan002> basic(@RequestHeader(value = "x-api-id") String api_id,
                                          @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                          @RequestBody ReqLoan002 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResLoan002 res = service.basic(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


  /**
   * 할부금융 업권 - 대출상품계좌 추가정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/loans/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResLoan003> detail(@RequestHeader(value = "x-api-id") String api_id,
                                           @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                           @RequestBody ReqLoan003 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResLoan003 res = service.detail(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


  /**
   * 할부금융 업권 - 대출상품계좌 거래내역 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/loans/transactions", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResLoan004> transactions(@RequestHeader(value = "x-api-id") String api_id,
                                                 @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                 @RequestBody ReqLoan004 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResLoan004 res = service.transactions(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
