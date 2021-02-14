package kr.mydata.apim.api;

import kr.mydata.apim.service.IRPService;
import kr.mydata.apim.vo.bank.ResBank010;
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
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/v1/irps", produces = "application/json; charset=UTF-8")
  public ResponseEntity listAccount(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP001 req) {

    try {
      ResIRP001 resIRP001 = service.listAccount(req, api_id, own_org_cd);
      return new ResponseEntity(resIRP001, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 보험 공통)
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/irps/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpBasic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP002 req) {

    try {
      ResIRP002 resIRP002 = service.irpBasic(req, api_id, own_org_cd);
      return new ResponseEntity(resIRP002, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통)
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/irps/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpDetail(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP003 req) {

    try {
      ResIRP003 resIRP003 = service.irpDetail(req, api_id, own_org_cd);
      return new ResponseEntity(resIRP003, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통)
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/irps/transactions", produces = "application/json; charset=UTF-8")
  public ResponseEntity irpTransactions(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd, ReqIRP004 req) {

    try {
      ResIRP004 resIRP004 = service.irpTransactions(req, api_id, own_org_cd);
      return new ResponseEntity(resIRP004, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
