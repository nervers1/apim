package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.CardService;
import kr.mydata.apim.vo.card.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Log4j2
@RestController
@RequestMapping(value = "/card")
public class CardController {

    private final CardService  service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public CardController(CardService service,
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
     * 카드업권 - 카드 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqCard001
     * @return ResponseEntity
     */
    @GetMapping(value = "/cards", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResCard001> listCards(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard001 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

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
    public ResponseEntity<ResCard002> cardBasic(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @NotEmpty(message = "card_id 값이 반드시 필요합니다.") @PathVariable(value = "card_id") String card_id,
                                                @Valid ReqCard002 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard002 resCard002 = service.cardBasic(req, api_id, own_org_cd, card_id);
        return new ResponseEntity<>(resCard002, HttpStatus.OK);
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
    public ResponseEntity<ResCard003> cardPoint(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard003 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/point");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard003 res = service.cardPoint(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard004> cardBills(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard004 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/bills");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard004 res = service.cardBills(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard005> cardBills(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard005 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/bills/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard005 res = service.cardBillsDetail(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard006> cardBills(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard006 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/payment");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard006 res = service.cardPayment(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard007> cardApprovalDomestic(@RequestHeader(value = "Authorization") String authorization,
                                                           @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                           @RequestHeader(value = "x-api-id") String api_id,
                                                           @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                           @NotEmpty(message = "card_id 값이 반드시 필요합니다.") @PathVariable(value = "card_id") String card_id,
                                                           @Valid ReqCard007 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-domestic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard007 res = service.cardApprovalDomestic(req, api_id, own_org_cd, card_id);
        return new ResponseEntity<>(res, HttpStatus.OK);

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
    public ResponseEntity<ResCard008> cardApprovalOverseas(@RequestHeader(value = "Authorization") String authorization,
                                                           @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                           @RequestHeader(value = "x-api-id") String api_id,
                                                           @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                           @NotEmpty(message = "card_id 값이 반드시 필요합니다.") @PathVariable(value = "card_id") String card_id,
                                                           @Valid ReqCard008 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-overseas");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("card_id : {}", card_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard008 res = service.cardApprovalOverseas(req, api_id, own_org_cd, card_id);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard009> cardLoans(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                @Valid ReqCard009 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-overseas");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard009 res = service.cardLoans(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard010> cardLoanRevolving(@RequestHeader(value = "Authorization") String authorization,
                                                        @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                        @RequestHeader(value = "x-api-id") String api_id,
                                                        @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                        @Valid ReqCard010 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-overseas");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard010 res = service.cardLoansRevolving(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard011> cardLoansShortTerm(@RequestHeader(value = "Authorization") String authorization,
                                                         @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                         @RequestHeader(value = "x-api-id") String api_id,
                                                         @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                         @Valid ReqCard011 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-overseas");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard011 res = service.cardLoansShortTerm(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
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
    public ResponseEntity<ResCard012> cardLoansLongTerm(@RequestHeader(value = "Authorization") String authorization,
                                                        @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                        @RequestHeader(value = "x-api-id") String api_id,
                                                        @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                        @Valid ReqCard012 req) throws Exception {

        api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-overseas");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCard012 res = service.cardLoansLongTerm(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
