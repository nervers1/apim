package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.EfinService;
import kr.mydata.apim.vo.efin.*;
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
@RequestMapping(value = "/efin")
public class EfinController {

    private final EfinService  service;
	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

    public EfinController(EfinService service,
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
     * 전자금융 - 전자지급수단 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin001
     * @return ResponseEntity
     */
    @GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin001> accounts(@RequestHeader(value = "Authorization") String authorization,
											   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											   @RequestHeader(value = "x-api-id") String api_id,
                                               @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                               @Valid ReqEfin001 req) throws Exception {

		api_id = checkApiId(api_id, "/efin/accounts");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin001 resInsu001 = service.accounts(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu001, HttpStatus.OK);

    }

    /**
     * 전자금융 - 전자지급수단 잔액정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin002
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/balance", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin002> balance(@RequestHeader(value = "Authorization") String authorization,
											  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											  @RequestHeader(value = "x-api-id") String api_id,
                                              @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                              @Valid @RequestBody ReqEfin002 req) throws Exception {

		api_id = checkApiId(api_id, "/efin/accounts/balance");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin002 resInsu002 = service.balance(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu002, HttpStatus.OK);

    }

    /**
     * 전자금융 - 전자지급수단 자동충전정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin003
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/charge", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin003> charge(@RequestHeader(value = "Authorization") String authorization,
											 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											 @RequestHeader(value = "x-api-id") String api_id,
                                             @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                             @Valid @RequestBody ReqEfin003 req) throws Exception {

		api_id = checkApiId(api_id, "/efin/accounts/charge");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin003 resInsu003 = service.charge(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu003, HttpStatus.OK);

    }

    /**
     * 전자금융 - 선불 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin004
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/prepaid-transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin004> prepaidTransactions(@RequestHeader(value = "Authorization") String authorization,
														  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
														  @RequestHeader(value = "x-api-id") String api_id,
                                                          @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                          @Valid @RequestBody ReqEfin004 req) throws Exception {

		api_id = checkApiId(api_id, "/efin/accounts/prepaid-transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin004 resInsu004 = service.prepaidTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu004, HttpStatus.OK);

    }

    /**
     * 전자금융 - 결제내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqEfin005
     * @return ResponseEntity
     */
    @PostMapping(value = "/accounts/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResEfin005> transactions(@RequestHeader(value = "Authorization") String authorization,
												   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
												   @RequestHeader(value = "x-api-id") String api_id,
                                                   @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                   @Valid @RequestBody ReqEfin005 req) throws Exception {

		api_id = checkApiId(api_id, "/efin/accounts/transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResEfin005 resEfin005 = service.transactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resEfin005, HttpStatus.OK);

    }
}
