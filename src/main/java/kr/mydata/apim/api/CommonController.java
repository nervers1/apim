package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
public class CommonController {

    private final CommonService common;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public CommonController(CommonService common,
                            JdbcTemplate jdbcTemplate) {
        this.common = common;
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
     * 공통 API - API 목록 조회 (공통)
     *
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @param req
     * @return
     * @thorws Exception
     */
    @GetMapping(value = "/{industry}/apis", produces = "application/json; charset=UTF-8")
    public ResponseEntity listApis(@RequestHeader(value = "Authorization", required = false) String authorization,
                                   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                   @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                   @PathVariable String industry,
                                   @Valid ReqCmn001 req) throws Exception {

        api_id = checkApiId(api_id, "/" + industry + "/apis");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCmn001 resCmn001 = common.listAPI(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resCmn001, HttpStatus.OK);
    }

    /**
     * 공통 API - 전송요구내역 조회 (공통)
     *
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @param req
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{industry}/consents", produces = "application/json; charset=UTF-8")
    public ResponseEntity listConsents(@RequestHeader(value = "Authorization") String authorization,
                                       @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                       @RequestHeader(value = "x-api-id", required = false) String api_id,
                                       @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                       @PathVariable String industry,
                                       @Valid ReqCmn002 req) throws Exception {

        api_id = checkApiId(api_id, "/" + industry + "/apis");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCmn002 resCmn002 = common.listConsents(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resCmn002, HttpStatus.OK);

    }


}

