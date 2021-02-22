package kr.mydata.apim.api;

import kr.mydata.apim.service.InvestService;
import kr.mydata.apim.vo.invest.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/v1/invest")
public class InvestController {

    private final InvestService service;

    public InvestController(InvestService service) {
        this.service = service;
    }

    /**
     * 금융투자업권 - 계좌 목록 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
    public ResponseEntity listAccount(@RequestHeader(value = "x-api-id") String api_id,
                                      @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                      @Valid ReqInvest001 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

		ResInvest001 resInvest001 = service.listAccount(req, api_id, own_org_cd);
		return new ResponseEntity<>(resInvest001, HttpStatus.OK);
    }

    /**
     * 금융투자업권 - 계좌 기본정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity listBasic(@RequestHeader(value = "x-api-id", required = false) String api_id,
                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                    @Valid @RequestBody ReqInvest002 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

		ResInvest002 resInvest002 = service.listBasic(req, api_id, own_org_cd);
		return new ResponseEntity<>(resInvest002, HttpStatus.OK);
    }

    /**
     * 금융투자업권 - 계좌 거래내역 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity listTransactions(@RequestHeader(value = "x-api-id") String api_id,
                                           @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                           @Valid @RequestBody ReqInvest003 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

		ResInvest003 resInvest003 = service.listTransactions(req, api_id, own_org_cd);
		return new ResponseEntity<>(resInvest003, HttpStatus.OK);
	}

    /**
     * 금융투자업권 - 계좌 상품정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/products", produces = "application/json; charset=UTF-8")
    public ResponseEntity listProducts(@RequestHeader(value = "x-api-id") String api_id,
                                       @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                       @Valid @RequestBody ReqInvest004 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

		ResInvest004 resInvest004 = service.listProducts(req, api_id, own_org_cd);
		return new ResponseEntity<>(resInvest004, HttpStatus.OK);
    }
}
