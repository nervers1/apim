package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.base.exception.AuthorizationException;
import kr.mydata.apim.service.InsuService;
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
@RequestMapping(value = "/insu")
public class InsuController {

    private final InsuService  service;
	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

    public InsuController(InsuService service,
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
     * 보험업권 - 보험 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu001
     * @return ResponseEntity
     */
    @GetMapping(value = "/insurances", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu001> listInsu(@RequestHeader(value = "Authorization") String authorization,
											   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                               @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                               @Valid ReqInsu001 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu001 resInsu001 = service.listInsu(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu001, HttpStatus.OK);
    }


    /**
     * 보험업권 - 보험 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu002
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu002> insuBasic(@RequestHeader(value = "Authorization") String authorization,
												@RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
												@RequestHeader(value = "x-api-id", required = false) String api_id,
                                                @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                @Valid @RequestBody ReqInsu002 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/basic");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu002 resInsu002 = service.insuBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu002, HttpStatus.OK);

    }


    /**
     * 보험업권 - 보험 특약정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu003
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/contracts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu003> insuContracts(@RequestHeader(value = "Authorization") String authorization,
													@RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
													@RequestHeader(value = "x-api-id", required = false) String api_id,
                                                    @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                    @Valid @RequestBody ReqInsu003 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/contracts");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu003 resInsu003 = service.insuContracts(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu003, HttpStatus.OK);

    }


    /**
     * 보험업권 - 자동차보험 정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu004
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/car", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu004> insuCar(@RequestHeader(value = "Authorization") String authorization,
											  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                              @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                              @Valid @RequestBody ReqInsu004 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/car");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu004 resInsu004 = service.insuCar(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu004, HttpStatus.OK);

    }


    /**
     * 보험업권 - 보험 납입정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu005
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/payment", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu005> insuPayment(@RequestHeader(value = "Authorization") String authorization,
												  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
												  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                  @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                  @Valid @RequestBody ReqInsu005 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/payment");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu005 resInsu005 = service.insuPayment(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu005, HttpStatus.OK);

    }


    /**
     * 보험업권 - 보험 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ResInsu006
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu006> insuTransactions(@RequestHeader(value = "Authorization") String authorization,
													   @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
													   @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                       @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                       @Valid @RequestBody ReqInsu006 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu006 resInsu006 = service.insuTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu006, HttpStatus.OK);

    }


    /**
     * 보험업권 - 자동차보험 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu007
     * @return ResponseEntity
     */
    @PostMapping(value = "/insurances/car/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu007> insuCarTransactions(@RequestHeader(value = "Authorization") String authorization,
														  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
														  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                          @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                          @Valid @RequestBody ReqInsu007 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/insurances/car/transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu007 resInsu007 = service.insuCarTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu007, HttpStatus.OK);

    }

    /**
     * 보험업권 - 대출상품 목록 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu008
     * @return ResponseEntity
     */
    @GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu008> loans(@RequestHeader(value = "Authorization") String authorization,
											@RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
											@RequestHeader(value = "x-api-id", required = false) String api_id,
                                            @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                            @Valid ReqInsu008 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/loans");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu008 resInsu008 = service.insuLoans(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu008, HttpStatus.OK);

    }

    /**
     * 보험업권 - 대출상품 기본정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu009
     * @return ResponseEntity
     */
    @PostMapping(value = "/loans/basic", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu009> insuLoansBasic(@RequestHeader(value = "Authorization") String authorization,
													 @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
													 @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                     @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                     @Valid @RequestBody ReqInsu009 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/loans/basic");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu009 resInsu009 = service.insuLoansBasic(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu009, HttpStatus.OK);

    }

    /**
     * 보험업권 - 대출상품 추가정보 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu010
     * @return ResponseEntity
     */
    @PostMapping(value = "/loans/detail", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu010> insuLoansDetail(@RequestHeader(value = "Authorization") String authorization,
													  @RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
													  @RequestHeader(value = "x-api-id", required = false) String api_id,
                                                      @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                      @Valid @RequestBody ReqInsu010 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/loans/detail");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu010 resInsu010 = service.insuLoansDetail(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu010, HttpStatus.OK);

    }

    /**
     * 보험업권 - 대출상품 거래내역 조회
     *
     * @param api_id     String
     * @param own_org_cd String
     * @param req        ReqInsu011
     * @return ResponseEntity
     */
    @PostMapping(value = "/loans/transactions", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResInsu011> insuLoansTransactions(@RequestHeader(value = "Authorization") String authorization,
															@RequestHeader(value = "X-FSI-SVC-DATA-KEY", required = false) String xFsiSvcDataKey,
															@RequestHeader(value = "x-api-id", required = false) String api_id,
                                                            @RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
                                                            @Valid @RequestBody ReqInsu011 req) throws Exception {

		api_id = checkApiId(api_id, "/insu/loans/transactions");
		own_org_cd = checkOwnOrgCd(own_org_cd, authorization, xFsiSvcDataKey);

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResInsu011 resInsu011 = service.insuLoansTransactions(req, api_id, own_org_cd);
        return new ResponseEntity<>(resInsu011, HttpStatus.OK);
    }
}
