package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.MgmtService;
import kr.mydata.apim.vo.mgmts.*;
import kr.mydata.apim.vo.mgmts.ReqMgmts001;
import kr.mydata.apim.vo.mgmts.ResMgmts001;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
public class MgmtsController {

    private final MgmtService service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public MgmtsController(MgmtService service,
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
     * 지원 API - 종합포털제공 접근토큰 발급
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/support/oauth/2.0/token", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts001> supportToken(@RequestHeader(value = "Authorization") String authorization,
                                                    @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                    @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                    @Valid ReqMgmts001 req) throws Exception {

        api_id = checkApiId(api_id, "/support/oauth/2.0/token");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts001 res = service.supportToken(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 지원 API - 기관정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/mgmts/orgs", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts002> orgs(@RequestHeader(value = "Authorization") String authorization,
                                            @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                            @RequestHeader(value = "x-api-id", required = false) String api_id,
                                            @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                            @Valid ReqMgmts002 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/orgs");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts002 res = service.orgs(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 지원 API - 서비스정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/mgmts/services", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts003> services(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                @Valid ReqMgmts003 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/services");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts003 res = service.services(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 지원 API - 통계자료 전송
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/mgmts/statistics", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts004> statistics(@RequestHeader(value = "Authorization") String authorization,
                                                  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                  @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                  @Valid @RequestBody ReqMgmts004 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/statistics");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts004 res = service.statistics(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 지원 API - 종합포털제공 접근토큰 발급
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/company/oauth/2.0/token", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts001> companyToken(@RequestHeader(value = "Authorization") String authorization,
                                                    @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                    @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                    @Valid ReqMgmts001 req) throws Exception {

        api_id = checkApiId(api_id, "/company/oauth/2.0/token");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts001 res = service.companyToken(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 지원 API - 데이터보유자 상태조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/mgmts/status", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts006> status(@RequestHeader(value = "Authorization") String authorization,
                                              @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                              @RequestHeader(value = "x-api-id", required = false) String api_id,
                                              @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                              @Valid ReqMgmts006 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/status");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts006 res = service.status(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 지원 API - 정보주체 별 전송요구 내역조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/mgmts/consents", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts007> consents(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                @Valid ReqMgmts007 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/consents");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts007 res = service.consents(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 지원 API - 통계자료 재전송 요청
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/mgmts/req-statistics", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts008> reqStatistics(@RequestHeader(value = "Authorization") String authorization,
                                                     @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                     @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                     @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                     @Valid ReqMgmts008 req) throws Exception {

        api_id = checkApiId(api_id, "/mgmts/req-statistics");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResMgmts008 res = service.reqStatistics(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
