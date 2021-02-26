package kr.mydata.apim.api;

import kr.mydata.apim.service.EfinService;
import kr.mydata.apim.vo.efin.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping(value = "/efin")
public class EfinController {

    public EfinController(EfinService service) {
        this.service = service;
    }

    private final EfinService service;

    /**
     * 전자금융 - 전자지급수단 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin001
     * @return ResponseEntity
     */
    @GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin001> accounts(@RequestHeader(value = "x-api-id") String api_id,
                                               @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                               @Valid ReqEfin001 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin001 resInsu001 = service.accounts(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu001, HttpStatus.OK);

    }

    /**
     * 전자금융 - 전자지급수단 잔액정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin002
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/balance", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin002> balance(@RequestHeader(value = "x-api-id") String api_id,
                                              @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                              @Valid @RequestBody ReqEfin002 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin002 resInsu002 = service.balance(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu002, HttpStatus.OK);

    }

    /**
     * 전자금융 - 전자지급수단 자동충전정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin003
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/charge", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin003> charge(@RequestHeader(value = "x-api-id") String api_id,
                                             @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                             @Valid @RequestBody ReqEfin003 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin003 resInsu003 = service.charge(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu003, HttpStatus.OK);

    }

    /**
     * 전자금융 - 선불 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin004
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/prepaid-transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin004> prepaidTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                                          @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                          @Valid @RequestBody ReqEfin004 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin004 resInsu004 = service.prepaidTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu004, HttpStatus.OK);

    }

    /**
     * 전자금융 - 결제내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin005
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin005> transactions(@RequestHeader(value = "x-api-id") String api_id,
                                                   @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                   @Valid @RequestBody ReqEfin005 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin005 resEfin005 = service.transactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resEfin005, HttpStatus.OK);

    }
}
