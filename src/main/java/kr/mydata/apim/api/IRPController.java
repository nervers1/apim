package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.base.exception.UnsupportedIndustryException;
import kr.mydata.apim.service.IRPService;
import kr.mydata.apim.vo.irp.*;
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
public class IRPController {

    private final IRPService service;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public IRPController(IRPService service,
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
     * 은행, 금투, 보험 이외 업권으로 요청 시 에러 발생
     *
     * @param industry
     * @throws Exception
     */
    private void checkIndustry(String industry) throws Exception {
        switch (industry) {
            case "bank":
            case "invest":
            case "insu":
                break;
            default:
                throw new UnsupportedIndustryException();
        }
    }

    /**
     * 개인형 IRP 계좌 목록 조회 (은행, 금투, 보험 공통)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqIRP001
     * @return ResIRP001
     */
    @GetMapping(value = "/{industry}/irps", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResIRP001> listAccount(@RequestHeader(value = "Authorization") String authorization,
                                                 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                 @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                 @PathVariable String industry,
                                                 @Valid ReqIRP001 req) throws Exception {

        checkIndustry(industry);

        api_id = checkApiId(api_id, "/" + industry + "/irps");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResIRP001 resIRP001 = service.listAccount(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resIRP001, HttpStatus.OK);
    }

    /**
     * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 보험 공통)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqIRP002
     * @return ResIRP002
     */
    @PostMapping(value = "/{industry}/irps/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResIRP002> irpBasic(@RequestHeader(value = "Authorization") String authorization,
                                              @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                              @RequestHeader(value = "x-api-id", required = false) String api_id,
                                              @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                              @PathVariable String industry,
                                              @Valid @RequestBody ReqIRP002 req) throws Exception {

        checkIndustry(industry);

        api_id = checkApiId(api_id, "/" + industry + "/irps/basic");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResIRP002 resIRP002 = service.irpBasic(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resIRP002, HttpStatus.OK);
    }

    /**
     * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqIRP003
     * @return ResIRP003
     */
    @PostMapping(value = "/{industry}/irps/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResIRP003> irpDetail(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                               @RequestHeader(value = "x-api-id", required = false) String api_id,
                                               @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                               @PathVariable String industry,
                                               @Valid @RequestBody ReqIRP003 req) throws Exception {

        checkIndustry(industry);

        api_id = checkApiId(api_id, "/" + industry + "/irps/detail");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResIRP003 resIRP003 = service.irpDetail(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resIRP003, HttpStatus.OK);
    }

    /**
     * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통)
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqIRP004
     * @return ResIRP004
     */
    @PostMapping(value = "/{industry}/irps/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResIRP004> irpTransactions(@RequestHeader(value = "Authorization") String authorization,
                                                     @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
                                                     @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                     @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                     @PathVariable String industry,
                                                     @Valid @RequestBody ReqIRP004 req) throws Exception {

        checkIndustry(industry);

        api_id = checkApiId(api_id, "/" + industry + "/irps/transactions");
        own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResIRP004 resIRP004 = service.irpTransactions(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resIRP004, HttpStatus.OK);
    }


}
