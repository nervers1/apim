package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.GinsuService;
import kr.mydata.apim.vo.ginsu.*;
import kr.mydata.apim.vo.insu.*;
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
@RequestMapping(value = "/ginsu")
public class GinsuController {

    private final GinsuService service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public GinsuController(GinsuService service,
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
     * 보증보험업권 - 보증보험 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu001
     * @return ResponseEntity
     */
    @GetMapping(value = "/insurances", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResGinsu001> listGinsu(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                 @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                 @Valid ReqGinsu001 req) throws Exception {

        api_id = checkApiId(api_id, "/ginsu/insurances");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResGinsu001 resGinsu001 = service.listGinsu(req, api_id, own_org_cd);
        return new ResponseEntity<>(resGinsu001, HttpStatus.OK);
    }


    /**
     * 보증보험업권 - 보증보험 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu002
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResGinsu002> ginsuBasic(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                 @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                 @Valid @RequestBody ReqGinsu002 req) throws Exception {

        api_id = checkApiId(api_id, "/ginsu/insurances/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResGinsu002 resGinsu002 = service.ginsuBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resGinsu002, HttpStatus.OK);

    }

    /**
     * 보증보험업권 - 보증보험 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ResInsu006
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResGinsu003> ginsuTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                         @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                         @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                         @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                         @Valid @RequestBody ReqGinsu003 req) throws Exception {

        api_id = checkApiId(api_id, "/ginsu/insurances/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResGinsu003 resGinsu003 = service.ginsuTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resGinsu003, HttpStatus.OK);

    }
}
