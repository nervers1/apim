package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.BankService;
import kr.mydata.apim.vo.bank.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping(value = "/bank")
@RestController
public class BankController {

    private final BankService  service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public BankController(BankService service,
                          JdbcTemplate jdbcTemplate) {
        this.service = service;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String checkApiId(String api_id, String uri) throws Exception {

        log.error("uri - {}", uri);

        if (ObjectUtils.isEmpty(api_id)) {
            // @formatter:off
            String preSql = "SELECT b.id "
                + "        FROM apx_api_resource a, apx_api_resource_method b "
                + "       WHERE a.uri = '" + uri + "'"
                + "         AND b.resource_version_id = a.target_version";
            // @formatter:on

            String preRes = jdbcTemplate.queryForObject(preSql, String.class);

            api_id = mapper.readValue(preRes, String.class);
        }

        return api_id;
    }

    public String checkOwnOrgCd(String own_org_cd, String authorization, String xFsiSvcDataKey) throws Exception {

        if (ObjectUtils.isEmpty(own_org_cd)) {
            if (authorization.startsWith("Bearer ")) {
                authorization = authorization.substring(7);
            }

            if(StringUtils.hasLength(xFsiSvcDataKey) && "Y".equals(xFsiSvcDataKey)) {
                // @formatter:off
                String ownOrgCdSql = "SELECT b.organization_id "
                    + "             FROM apx_oauth_token a, apx_app b "
                    + "            WHERE a.access_token = '" + authorization + "'"
                    + "              AND b.id = a.app_id";

                String ownOrgCdRes = jdbcTemplate.queryForObject(ownOrgCdSql,
                                                                 String.class);
                // @formatter:on

                if(null == ownOrgCdRes || !StringUtils.hasLength(ownOrgCdRes)) {
                    throw new AuthorizationException();
                }

                own_org_cd = mapper.readValue(ownOrgCdRes,
                                              String.class);
            } else {
                own_org_cd = "0000000000";
            }
        }

        return own_org_cd;
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
    public ResponseEntity<ResBank001> accounts(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                               @RequestHeader(value = "x-api-id", required = false) String api_id,
                                               @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                               @Valid ReqBank001 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank001 res = service.listAccount(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResBank002> accountsBasic(@RequestHeader(value = "Authorization") String authorization,
                                                    @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                    @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                    @Valid @RequestBody ReqBank002 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/deposit/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank002 res = service.inqBasicInfo(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResBank003> accountDetail(@RequestHeader(value = "Authorization") String authorization,
                                                    @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                    @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                    @Valid @RequestBody ReqBank003 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/deposit/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank003 resBank003 = service.addtionalInfo(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank003, HttpStatus.OK);
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
    public ResponseEntity<ResBank004> listTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                       @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                       @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                       @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                       @Valid @RequestBody ReqBank004 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/deposit/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank004 result = service.listTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
    public ResponseEntity<ResBank005> investBasic(@RequestHeader(value = "Authorization") String authorization,
                                                  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                  @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                  @Valid @RequestBody ReqBank005 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/invest/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank005 resBank005 = service.investBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank005, HttpStatus.OK);
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
    public ResponseEntity<ResBank006> investDetail(@RequestHeader(value = "Authorization") String authorization,
                                                   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                   @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                   @Valid @RequestBody ReqBank006 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/invest/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank006 resBank006 = service.investDetail(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank006, HttpStatus.OK);
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
    public ResponseEntity<ResBank007> investTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                         @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                         @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                         @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                         @Valid @RequestBody ReqBank007 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/invest/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank007 resBank007 = service.investTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank007, HttpStatus.OK);
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
    public ResponseEntity<ResBank008> loanBasic(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                @Valid @RequestBody ReqBank008 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/loan/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank008 resBank008 = service.loanBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank008, HttpStatus.OK);
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
    public ResponseEntity<ResBank009> loanDetail(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                 @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                 @Valid @RequestBody ReqBank009 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/loan/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank009 resBank009 = service.loanDetail(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank009, HttpStatus.OK);
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
    public ResponseEntity<ResBank010> loanTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                       @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                       @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                       @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                       @Valid @RequestBody ReqBank010 req) throws Exception {

        api_id = checkApiId(api_id, "/bank/accounts/loan/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResBank010 resBank010 = service.loanTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resBank010, HttpStatus.OK);
    }
}
