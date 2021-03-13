package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.TelecomService;
import kr.mydata.apim.vo.ginsu.*;
import kr.mydata.apim.vo.telecom.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping(value = "/telecom")
public class TelecomController {

    private final TelecomService service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public TelecomController(TelecomService service,
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

                if (null == ownOrgCdRes || !StringUtils.hasLength(ownOrgCdRes)) {
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
     * 통신업권 - 통신 계약 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu001
     * @return ResponseEntity
     */
    @GetMapping(value = "/telecoms", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResTelecom001> listGinsu(@RequestHeader(value = "Authorization") String authorization,
                                                   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                   @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                   @Valid ReqTelecom001 req) throws Exception {

        api_id = checkApiId(api_id, "/telecom/telecoms");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResTelecom001 resTelecom001 = service.listTelecom(req, api_id, own_org_cd);
        return new ResponseEntity<>(resTelecom001, HttpStatus.OK);
    }


    /**
     * 통신업권 - 청구 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu002
     * @return ResponseEntity
     */
    @GetMapping(value = "/telecoms/bills", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResTelecom002> telecomBills(@RequestHeader(value = "Authorization") String authorization,
                                                      @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                      @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                      @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                      @Valid ReqTelecom002 req) throws Exception {

        api_id = checkApiId(api_id, "/telecom/telecoms/bills");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResTelecom002 resTelecom002 = service.telecomBills(req, api_id, own_org_cd);
        return new ResponseEntity<>(resTelecom002, HttpStatus.OK);

    }

    /**
     * 통신업권 - 통신 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ResInsu006
     * @return ResponseEntity
     */
    @PostMapping(value = "/telecoms/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResTelecom003> ginsuTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                           @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                           @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                           @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                           @Valid @RequestBody ReqTelecom003 req) throws Exception {

        api_id = checkApiId(api_id, "/telecom/telecoms/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResTelecom003 resTelecom003 = service.telecomTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resTelecom003, HttpStatus.OK);

    }

    /**
     * 통신업권 - 결제내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ResInsu006
     * @return ResponseEntity
     */
    @PostMapping(value = "/telecoms/paid-transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResTelecom004> telecomPaidTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                                 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                                 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                                 @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                                 @Valid @RequestBody ReqTelecom004 req) throws Exception {

        api_id = checkApiId(api_id, "/telecom/telecoms/piad-transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResTelecom004 resTelecom004 = service.telecomPaidTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resTelecom004, HttpStatus.OK);

    }
}
