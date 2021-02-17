package kr.mydata.apim.api;

import kr.mydata.apim.service.IRPService;
import kr.mydata.apim.vo.irp.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class IRPController {

  public IRPController(IRPService service) {
    this.service = service;
  }

  private final IRPService service;


  /**
   * 개인형 IRP 계좌 목록 조회 (은행, 금투, 보험 공통)
   * @param api_id String
   * @param own_org_cd String
   * @param req ReqIRP001
   * @return ResIRP001
   */
  @GetMapping(value = "/v1/irps", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResIRP001> listAccount(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP001 req) {

    try {
      ResIRP001 resIRP001 = service.listAccount(req, api_id, own_org_cd);
      return new ResponseEntity<>(resIRP001, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 보험 공통)
   * @param api_id String
   * @param own_org_cd String
   * @param req ReqIRP002
   * @return ResIRP002
   */
  @PostMapping(value = "/irps/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResIRP002> irpBasic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP002 req) {

    try {
      ResIRP002 resIRP002 = service.irpBasic(req, api_id, own_org_cd);
      return new ResponseEntity<>(resIRP002, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통)
   * @param api_id String
   * @param own_org_cd String
   * @param req ReqIRP003
   * @return ResIRP003
   */
  @PostMapping(value = "/irps/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResIRP003> irpDetail(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP003 req) {

    try {
      ResIRP003 resIRP003 = service.irpDetail(req, api_id, own_org_cd);
      return new ResponseEntity<>(resIRP003, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통)
   * @param api_id String
   * @param own_org_cd String
   * @param req ReqIRP004
   * @return ResIRP004
   */
  @PostMapping(value = "/irps/transactions", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResIRP004> irpTransactions(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP004 req) {

    try {
      ResIRP004 resIRP004 = service.irpTransactions(req, api_id, own_org_cd);
      return new ResponseEntity<>(resIRP004, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
