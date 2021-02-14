package kr.mydata.apim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.service.BankService;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class BankController {

  private final BankService service;
  private final ObjectMapper mapper = new ObjectMapper();
  final JdbcTemplate jdbcTemplate;

  public BankController(BankService service, JdbcTemplate jdbcTemplate) {
    this.service = service;
    this.jdbcTemplate = jdbcTemplate;
  }


  /**
   * 은행업권 : 계좌 목록 조회
   *
   * @param api_id
   * @param req
   * @return ResBank001
   */
  @GetMapping(value = "/v1/bank/accounts")
  public ResponseEntity accounts(@RequestHeader(value = "x-api-id") String api_id,
                                 @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                 ReqBank001 req) {
    log.info("api_id : {}", api_id);
    log.info("RequestBody : {}", req);

    try {
      ResBank001 res = service.listAccount(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * 은행업권 - 수신계좌 기본정보 조회(은행)
   *
   * @param api_id
   * @param req
   * @return ReqBank002
   */
  @PostMapping(value = "/v1/bank/accounts/deposit/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity accountsBasic(@RequestHeader(value = "x-api-id") String api_id,
                                      @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                      @RequestBody ReqBank002 req) {

    log.info("api_id : {}", api_id);
    log.info("RequestBody : {}", req);

    try {
      ResBank002 res = service.inqBasicInfo(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 은행업권 - 수신계좌 추가정보 조회(은행)
   *
   * @param api_id
   * @param req
   * @return
   */
  @PostMapping(value = "/v1/bank/accounts/deposit/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity<ResBank003> accountDetail(@RequestHeader(value = "x-api-id") String api_id,
                                                  @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                  @RequestBody ReqBank003 req) {
    log.info("api_id : {}", api_id);
    log.info("RequestBody : {}", req);

    try {
      ResBank003 resBank003 = service.addtionalInfo(req, api_id, own_org_cd);
      return new ResponseEntity<>(resBank003, HttpStatus.OK);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 은행업권 - 수신계좌 거래내역 조회
   *
   * @param api_id
   * @param req
   * @return ResBank004
   */
  @PostMapping(value = "/v1/bank/accounts/deposit/transactions", produces = "application/json; charset=UTF-8")
  public ResponseEntity listTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                         @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                         @RequestBody ReqBank004 req) {

    log.info("api_id : {}", api_id);
    log.info("req : {}", req);

    try {
      ResBank004 result = service.listTransactions(req, api_id, own_org_cd);
      return new ResponseEntity(result, HttpStatus.OK);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 은행업권 - 투자상품계좌 기본정보 조회
   *
   * @param api_id
   * @param req
   * @return ResBank005
   * JSON DateTimeFormatter Type Error : "20210101000000"에 대한 Deserialize 방법이 없음.
   * 2021-02-07 17:50:44.883 [http-nio-8080-exec-1] WARN o.s.w.s.m.s.DefaultHandlerExceptionResolver - Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `java.time.LocalDateTime` from String "20210101000000": Failed to deserialize java.time.LocalDateTime: (java.time.format.DateTimeParseException) Text '20210101000000' could not be parsed at index 0; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.time.LocalDateTime` from String "20210101000000": Failed to deserialize java.time.LocalDateTime: (java.time.format.DateTimeParseException) Text '20210101000000' could not be parsed at index 0
   * at [Source: (PushbackInputStream); line: 4, column: 19] (through reference chain: kr.mydata.apim.vo.bank.ReqBank004["from_dtime"])]
   */
  @PostMapping(value = "/v1/bank/accounts/invest/basic", produces = "application/json; charset=UTF-8")
  public ResponseEntity investBasic(@RequestHeader(value = "x-api-id") String api_id,
                                    @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                    @RequestBody ReqBank005 req) {

    log.info("api_id : {}", api_id);
    log.info("req : {}", req);

    try {
      ResBank005 resBank005 = service.investBasic(req, api_id, own_org_cd);
      return new ResponseEntity(resBank005, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 은행업권 - 투자상품계좌 추가정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/v1/bank/accounts/invest/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity investDetail(@RequestHeader(value = "x-api-id") String api_id,
                                     @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                     @RequestBody ReqBank006 req) {
    log.info("api_id : {}", api_id);
    log.info("req : {}", req);

    try {
      ResBank006 resBank006 = service.investDetail(req, api_id, own_org_cd);
      return new ResponseEntity(resBank006, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
