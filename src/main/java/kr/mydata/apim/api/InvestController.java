package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.InvestService;
import kr.mydata.apim.vo.invest.*;
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
@RequestMapping(value = "/invest")
public class InvestController {

    private final InvestService service;
	private final ObjectMapper  mapper = new ObjectMapper();
	private final JdbcTemplate  jdbcTemplate;

    public InvestController(InvestService service,
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
     * 금융투자업권 - 계좌 목록 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
    public ResponseEntity listAccount(@RequestHeader(value = "Authorization") String authorization,
									  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
									  @RequestHeader(value = "x-api-id") String api_id,
                                      @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                      @Valid ReqInvest001 req) throws Exception {

		api_id = checkApiId(api_id, "/invest/accounts");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

		log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInvest001 resInvest001 = service.listAccount(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInvest001, HttpStatus.OK);
    }

    /**
     * 금융투자업권 - 계좌 기본정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity listBasic(@RequestHeader(value = "Authorization") String authorization,
									@RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
									@RequestHeader(value = "x-api-id", required = false) String api_id,
                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                    @Valid @RequestBody ReqInvest002 req) throws Exception {

		api_id = checkApiId(api_id, "/invest/accounts/basic");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

		log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInvest002 resInvest002 = service.listBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInvest002, HttpStatus.OK);
    }

    /**
     * 금융투자업권 - 계좌 거래내역 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity listTransactions(@RequestHeader(value = "Authorization") String authorization,
										   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
										   @RequestHeader(value = "x-api-id") String api_id,
                                           @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                           @Valid @RequestBody ReqInvest003 req) throws Exception {

		api_id = checkApiId(api_id, "/invest/accounts/transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

		log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInvest003 resInvest003 = service.listTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInvest003, HttpStatus.OK);
    }

    /**
     * 금융투자업권 - 계좌 상품정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/accounts/products", produces = "application/json; charset=UTF-8")
    public ResponseEntity listProducts(@RequestHeader(value = "Authorization") String authorization,
									   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
									   @RequestHeader(value = "x-api-id") String api_id,
                                       @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                       @Valid @RequestBody ReqInvest004 req) throws Exception {

		api_id = checkApiId(api_id, "/invest/accounts/products");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

		log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInvest004 resInvest004 = service.listProducts(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInvest004, HttpStatus.OK);
    }
}
