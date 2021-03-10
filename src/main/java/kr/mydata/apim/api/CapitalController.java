package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.CapitalService;
import kr.mydata.apim.vo.capital.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping(value = "/capital")
@RestController
public class CapitalController {

    private final CapitalService service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public CapitalController(CapitalService service,
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

            if (StringUtils.hasLength(xFsiSvcDataKey) && "Y".equals(xFsiSvcDataKey)) {
                // @formatter:off
                String ownOrgCdSql = "SELECT b.organization_id "
                        + "             FROM apx_oauth_token a, apx_app b "
                        + "            WHERE a.access_token = '" + authorization + "'"
                        + "              AND b.id = a.app_id";

                String ownOrgCdRes = jdbcTemplate.queryForObject(ownOrgCdSql,
                        String.class);
                // @formatter:on

                if (!StringUtils.hasLength(ownOrgCdRes)) {
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
     * 할부금융 업권 - 계좌 목록 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResLoan001> loans(@RequestHeader(value = "Authorization") String authorization,
                                            @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                            @RequestHeader(value = "x-api-id", required = false) String api_id,
                                            @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                            @Valid ReqLoan001 req) throws Exception {

        api_id = checkApiId(api_id, "/capital/loans");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResLoan001 res = service.loans(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    /**
     * 할부금융 업권 - 대출상품계좌 기본정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/loans/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResLoan002> basic(@RequestHeader(value = "Authorization") String authorization,
                                            @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                            @RequestHeader(value = "x-api-id", required = false) String api_id,
                                            @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                            @Valid @RequestBody ReqLoan002 req) throws Exception {

        api_id = checkApiId(api_id, "/capital/loans/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResLoan002 res = service.basic(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }


    /**
     * 할부금융 업권 - 대출상품계좌 추가정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/loans/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResLoan003> detail(@RequestHeader(value = "Authorization") String authorization,
                                             @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                             @RequestHeader(value = "x-api-id", required = false) String api_id,
                                             @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                             @Valid @RequestBody ReqLoan003 req) throws Exception {

        api_id = checkApiId(api_id, "/capital/loans/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResLoan003 res = service.detail(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }


    /**
     * 할부금융 업권 - 대출상품계좌 거래내역 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/loans/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResLoan004> transactions(@RequestHeader(value = "Authorization") String authorization,
                                                   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                   @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                   @Valid @RequestBody ReqLoan004 req) throws Exception {

        api_id = checkApiId(api_id, "/capital/loans/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResLoan004 res = service.transactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
}
