package kr.mydata.apim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.service.BankService;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping(value = "/v1/bank")
@RestController
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    /**
     * 은행업권 : 계좌 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank001
     * @return ResBank001
     */
    @GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank001> accounts(@RequestHeader(value = "x-api-id") String api_id,
                                               @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                               @Valid ReqBank001 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank001 res = service.listAccount(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 은행업권 - 수신계좌 기본정보 조회(은행)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank002
     * @return ResBank002
     */
    @PostMapping(value = "/accounts/deposit/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank002> accountsBasic(@RequestHeader(value = "x-api-id") String api_id,
                                                    @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                    @Valid @RequestBody ReqBank002 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank002 res = service.inqBasicInfo(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 은행업권 - 수신계좌 추가정보 조회(은행)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank003
     * @return ResBank003
     */
    @PostMapping(value = "/accounts/deposit/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank003> accountDetail(@RequestHeader(value = "x-api-id") String api_id,
                                                    @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                    @Valid @RequestBody ReqBank003 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

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
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank004
     * @return ResBank004
     */
    @PostMapping(value = "/accounts/deposit/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank004> listTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                                       @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                       @Valid @RequestBody ReqBank004 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank004 result = service.listTransactions(req, api_id, own_org_cd);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 은행업권 - 투자상품계좌 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank005
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/invest/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank005> investBasic(@RequestHeader(value = "x-api-id") String api_id,
                                                  @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                  @Valid @RequestBody ReqBank005 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank005 resBank005 = service.investBasic(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank005, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 은행업권 - 투자상품계좌 추가정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank006
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/invest/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank006> investDetail(@RequestHeader(value = "x-api-id") String api_id,
                                                   @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                   @Valid @RequestBody ReqBank006 req) {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank006 resBank006 = service.investDetail(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank006, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 은행업권 - 투자상품계좌 추가정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank007
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/invest/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank007> investTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                                         @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                         @Valid @RequestBody ReqBank007 req) {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);
        try {
            ResBank007 resBank007 = service.investTransactions(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank007, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 은행업권 - 대출상품계좌 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank008
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/loan/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank008> loanBasic(@RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid @RequestBody ReqBank008 req) {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);
        try {
            ResBank008 resBank008 = service.loanBasic(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank008, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 은행업권 - 대출상품계좌 추가정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank009
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/loan/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank009> loanDetail(@RequestHeader(value = "x-api-id") String api_id,
                                                 @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                 @Valid @RequestBody ReqBank009 req) {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);
        try {
            ResBank009 resBank009 = service.loanDetail(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank009, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 은행업권 - 대출상품계좌 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqBank010
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/loan/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResBank010> loanTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                                       @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                       @Valid @RequestBody ReqBank010 req) {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResBank010 resBank010 = service.loanTransactions(req, api_id, own_org_cd);
            return new ResponseEntity<>(resBank010, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
