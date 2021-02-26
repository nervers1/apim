package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.AuthService;
import kr.mydata.apim.vo.auth.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequestMapping(value = "/oauth/2.0")
@RestController
public class OAuthController {

    private final AuthService  service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public OAuthController(AuthService service,
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

    @GetMapping(value = "/authorize", produces = "application/json; charset=UTF-8")
    public ResponseEntity authorize(@RequestHeader(value = "Authorization") String authorization,
                                    @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                    @RequestHeader(value = "x-api-id", required = false) String api_id,
                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                    @Valid ReqAuth001 req) throws Exception {

        api_id = checkApiId(api_id, "/oauth/2.0/authorize");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResAuth001 res = service.authorize(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 접근토큰 발급 요청
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/token", produces = "application/json; charset=UTF-8")
    public ResponseEntity token(@RequestHeader(value = "Authorization") String authorization,
                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                @RequestHeader(value = "x-api-id", required = false) String api_id,
                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                @Valid ReqAuth002 req) throws Exception {

        api_id = checkApiId(api_id, "/oauth/2.0/token");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResAuth002 res = service.token(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 접근토큰 갱신
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/token", produces = "application/json; charset=UTF-8")
    public ResponseEntity token(@RequestHeader(value = "Authorization") String authorization,
                                @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                @RequestHeader(value = "x-api-id", required = false) String api_id,
                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                @Valid ReqAuth003 req) throws Exception {

        api_id = checkApiId(api_id, "/oauth/2.0/token");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResAuth003 res = service.token(req, api_id, own_org_cd);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 접근토큰 폐기
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     */
    @GetMapping(value = "/revoke", produces = "application/json; charset=UTF-8")
    public void revoke(@RequestHeader(value = "Authorization") String authorization,
                       @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                       @RequestHeader(value = "x-api-id", required = false) String api_id,
                       @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                       @Valid ReqAuth004 req) throws Exception {

        api_id = checkApiId(api_id, "/oauth/2.0/revoke");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        service.revoke(req, api_id, own_org_cd);
    }
}
