package kr.mydata.apim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.service.CardService;
import kr.mydata.apim.vo.card.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/v1/card")
public class CardController {

  private final CardService service;

  public CardController(CardService service) {
    this.service = service;
  }


  /**
   * 카드업권 - 카드 목록 조회
   *
   * @param api_id
   * @param req
   * @return
   */
  @GetMapping(value = "/cards", produces = "application/json; charset=UTF-8")
  public ResponseEntity listCards(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  @ModelAttribute ReqCard001 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard001 resCard001 = service.listCard(req, api_id, own_org_cd);
      return new ResponseEntity(resCard001, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 - 카드 기본정보 조회
   *
   * @param api_id
   * @param req
   * @return http://localhost:8080/v1/card/cards/11345678923?org_code=0000000&search_timestamp=20210101221111
   */
  @GetMapping(value = "/cards/{card_id}", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardBasic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  @PathVariable(value = "card_id") String card_id,
                                  ReqCard002 req) {

    log.info("api_id : {}", api_id);
    log.info("card_id : {}", card_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard002 resCard002 = service.cardBasic(req, api_id, own_org_cd, card_id);
      return new ResponseEntity(resCard002, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 : 포인트 정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "/cards/point", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardPoint(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqCard003 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);

    try {
      ResCard003 res = service.cardPoint(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 : 청구 기본정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "/cards/bills", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqCard004 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);

    try {
      ResCard004 res = service.cardBills(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 : 청구 추가정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "/cards/bills/detail", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqCard005 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);

    try {
      ResCard005 res = service.cardBillsDetail(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 카드업권 : 결제정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping(value = "/cards/payment", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqCard006 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    try {
      ResCard006 res = service.cardPayment(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 카드업권 - 국내 승인내역 조회
   *
   * @param api_id
   * @param req
   * @return http://localhost:8080/v1/card/cards/10456243512223/approval-domestic?org_code=0000000&from_dtime=20210101000000&to_dtime=20210501235959&limit=20
   */
  @GetMapping(value = "/cards/{card_id}/approval-domestic", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardApprovalDomestic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                             @PathVariable(value = "card_id") String card_id,
                                             ReqCard007 req) {

    log.info("api_id : {}", api_id);
    log.info("card_id : {}", card_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard007 res = service.cardApprovalDomestic(req, api_id, own_org_cd, card_id);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 카드업권 - 해외 승인내역 조회
   *
   * @param api_id
   * @param req
   * @return http://localhost:8080/v1/card/cards/10456243512223/approval-domestic?org_code=0000000&from_dtime=20210101000000&to_dtime=20210501235959&limit=20
   */
  @GetMapping(value = "/cards/{card_id}/approval-overseas", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardApprovalOverseas(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                             @PathVariable(value = "card_id") String card_id,
                                             ReqCard008 req) {

    log.info("api_id : {}", api_id);
    log.info("card_id : {}", card_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard008 res = service.cardApprovalOverseas(req, api_id, own_org_cd, card_id);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 - 대출상품 목록 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardLoans(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqCard009 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard009 res = service.cardLoans(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * 카드업권 - 리볼빙 정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/loans/revolving", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardLoanRevolving(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                          ReqCard010 req) {
    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);
    try {
      ResCard010 res = service.cardLoansRevolving(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 카드업권 - 단기대출 정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/loans/short-term", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardLoansShortTerm(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                          ReqCard011 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard011 res = service.cardLoansShortTerm(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 카드업권 - 장기대출 정보 조회
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/loans/long-term", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardLoansLongTerm(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                          ReqCard012 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResCard012 res = service.cardLoansLongTerm(req, api_id, own_org_cd);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
