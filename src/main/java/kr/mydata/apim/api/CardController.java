package kr.mydata.apim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.service.CardService;
import kr.mydata.apim.vo.card.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard001
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard001> listCards(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard001 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

		ResCard001 resCard001 = service.listCard(req, api_id, own_org_cd);
		return new ResponseEntity<>(resCard001, HttpStatus.OK);
    }

    /**
     * 카드업권 - 카드 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param card_id    String
     * @param req        ReqCard002
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/{card_id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard002> cardBasic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @NotEmpty(message = "card_id 값이 반드시 필요합니다.") @PathVariable(value = "card_id") String card_id,
                                                ReqCard002 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard002 resCard002 = service.cardBasic(req, api_id, own_org_cd, card_id);
            return new ResponseEntity<>(resCard002, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 카드업권 : 포인트 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard003
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/point", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard003> cardPoint(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqCard003 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);

        try {
            ResCard003 res = service.cardPoint(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 카드업권 : 청구 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard004
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/bills", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard004> cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqCard004 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);

        try {
            ResCard004 res = service.cardBills(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 카드업권 : 청구 추가정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard005
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/bills/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard005> cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqCard005 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);

        try {
            ResCard005 res = service.cardBillsDetail(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 카드업권 : 결제정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard006
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/payment", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard006> cardBills(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqCard006 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        try {
            ResCard006 res = service.cardPayment(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 카드업권 - 국내 승인내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param card_id    String
     * @param req        ReqCard007
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/{card_id}/approval-domestic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard007> cardApprovalDomestic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                           @PathVariable(value = "card_id") String card_id,
                                                           ReqCard007 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard007 res = service.cardApprovalDomestic(req, api_id, own_org_cd, card_id);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 카드업권 - 해외 승인내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param card_id    String
     * @param req        ReqCard008
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards/{card_id}/approval-overseas", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard008> cardApprovalOverseas(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                           @PathVariable(value = "card_id") String card_id,
                                                           ReqCard008 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard008 res = service.cardApprovalOverseas(req, api_id, own_org_cd, card_id);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 카드업권 - 대출상품 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard009
     * @return ResponseEntity
     */
    @GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard009> cardLoans(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqCard009 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard009 res = service.cardLoans(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 카드업권 - 리볼빙 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard010
     * @return ResponseEntity
     */
    @GetMapping(value = "/loans/revolving", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard010> cardLoanRevolving(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                        ReqCard010 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);
        try {
            ResCard010 res = service.cardLoansRevolving(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 카드업권 - 단기대출 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard011
     * @return ResponseEntity
     */
    @GetMapping(value = "/loans/short-term", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard011> cardLoansShortTerm(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                         ReqCard011 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard011 res = service.cardLoansShortTerm(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 카드업권 - 장기대출 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard012
     * @return ResponseEntity
     */
    @GetMapping(value = "/loans/long-term", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard012> cardLoansLongTerm(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                        ReqCard012 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResCard012 res = service.cardLoansLongTerm(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
