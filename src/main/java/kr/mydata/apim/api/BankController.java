package kr.mydata.apim.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mydata.apim.service.BankService;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.service.IRPService;
import kr.mydata.apim.vo.ErrorResponse;
import kr.mydata.apim.vo.bank.ReqBank001;
import kr.mydata.apim.vo.bank.ReqBank002;
import kr.mydata.apim.vo.bank.ReqBank003;
import kr.mydata.apim.vo.bank.ReqBank004;
import kr.mydata.apim.vo.bank.ReqBank005;
import kr.mydata.apim.vo.bank.ReqBank006;
import kr.mydata.apim.vo.bank.ReqBank007;
import kr.mydata.apim.vo.bank.ReqBank008;
import kr.mydata.apim.vo.bank.ReqBank009;
import kr.mydata.apim.vo.bank.ReqBank010;
import kr.mydata.apim.vo.bank.ResBank001;
import kr.mydata.apim.vo.bank.ResBank002;
import kr.mydata.apim.vo.bank.ResBank003;
import kr.mydata.apim.vo.bank.ResBank004;
import kr.mydata.apim.vo.bank.ResBank005;
import kr.mydata.apim.vo.bank.ResBank006;
import kr.mydata.apim.vo.bank.ResBank007;
import kr.mydata.apim.vo.bank.ResBank008;
import kr.mydata.apim.vo.bank.ResBank009;
import kr.mydata.apim.vo.bank.ResBank010;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import kr.mydata.apim.vo.irp.ReqIRP001;
import kr.mydata.apim.vo.irp.ReqIRP002;
import kr.mydata.apim.vo.irp.ReqIRP003;
import kr.mydata.apim.vo.irp.ReqIRP004;
import kr.mydata.apim.vo.irp.ResIRP001;
import kr.mydata.apim.vo.irp.ResIRP002;
import kr.mydata.apim.vo.irp.ResIRP003;
import kr.mydata.apim.vo.irp.ResIRP004;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/bank")
@SuppressWarnings("rawtypes")
public class BankController {

	@Autowired
	private BankService bankService;

	@Autowired
	private IRPService irpService;

	@Autowired
	private CommonService commonService;

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public BankController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private ResponseEntity<ErrorResponse> returnErrorMessage(Exception e) {
		e.printStackTrace();

		ErrorResponse er = new ErrorResponse();
		er.setRsp_code("90001");
		er.setRsp_msg("Internal server error occured");

		return new ResponseEntity<ErrorResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String checkApiId(String api_id, String uri) throws JsonMappingException, JsonProcessingException {

		log.error("api_id - {}", api_id);
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

	private String checkOwnOrgCd(String own_org_cd, String authorization)
			throws JsonMappingException, JsonProcessingException {

		log.error("own_org_cd - {}", own_org_cd);
		log.error("authorization - {}", authorization);

		if (ObjectUtils.isEmpty(own_org_cd)) {
			if (authorization.startsWith("Bearer ")) {
				authorization = authorization.substring(7);
			}

			// @formatter:off
			String ownOrgCdSql = "SELECT b.organization_id " 
					+ "             FROM apx_oauth_token a, apx_app b "
					+ "            WHERE a.access_token = '" + authorization + "'"
					+ "              AND b.id = a.app_id";

			String ownOrgCdRes = jdbcTemplate.queryForObject(ownOrgCdSql, String.class);
			// @formatter:on

			own_org_cd = mapper.readValue(ownOrgCdRes, String.class);
		}

		return own_org_cd;
	}

	/**
	 * 은행업권 : 계좌 목록 조회
	 *
	 * @param api_id
	 * @param req
	 * @return ResBank001
	 */
	@GetMapping(value = "/accounts", produces = "application/json; charset=UTF-8")
	public ResponseEntity accounts(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqBank001 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/accounts");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank001 res = bankService.accounts(req, api_id, own_org_cd);

			return new ResponseEntity<ResBank001>(res, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}

	}

	/**
	 * 은행업권 - 수신계좌 기본정보 조회(은행)
	 *
	 * @param api_id
	 * @param req
	 * @return ReqBank002
	 */
	@PostMapping(value = "/accounts/deposit/basic", produces = "application/json; charset=UTF-8")
	public ResponseEntity accountBasic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank002 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/deposit/basic");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank002 res = bankService.accountBasic(req, api_id, own_org_cd);

			return new ResponseEntity<ResBank002>(res, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 수신계좌 추가정보 조회(은행)
	 *
	 * @param api_id
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/deposit/detail", produces = "application/json; charset=UTF-8")
	public ResponseEntity accountDetail(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank003 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/deposit/detail");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank003 resBank003 = bankService.accountDetail(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank003>(resBank003, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 수신계좌 거래내역 조회
	 *
	 * @param api_id
	 * @param req
	 * @return ResBank004
	 */
	@PostMapping(value = "/accounts/deposit/transactions", produces = "application/json; charset=UTF-8")
	public ResponseEntity accountTransactions(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank004 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/deposit/transactions");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank004 result = bankService.accountTransactions(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank004>(result, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 투자상품계좌 기본정보 조회
	 *
	 * @param api_id
	 * @param req
	 * @return ResBank005
	 */
	@PostMapping(value = "/accounts/invest/basic", produces = "application/json; charset=UTF-8")
	public ResponseEntity investBasic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank005 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/invest/basic");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank005 resBank005 = bankService.investBasic(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank005>(resBank005, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 투자상품계좌 추가정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/invest/detail", produces = "application/json; charset=UTF-8")
	public ResponseEntity investDetail(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank006 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/invest/detail");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank006 resBank006 = bankService.investDetail(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank006>(resBank006, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 투자상품계좌 추가정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/invest/transactions", produces = "application/json; charset=UTF-8")
	public ResponseEntity investTransactions(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank007 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/invest/transactions");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank007 resBank007 = bankService.investTransactions(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank007>(resBank007, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 대출상품계좌 기본정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/loan/basic", produces = "application/json; charset=UTF-8")
	public ResponseEntity loanBasic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank008 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/loan/basic");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank008 resBank008 = bankService.loanBasic(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank008>(resBank008, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 대출상품계좌 추가정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/loan/detail", produces = "application/json; charset=UTF-8")
	public ResponseEntity loanDetail(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank009 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/loan/detail");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank009 resBank009 = bankService.loanDetail(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank009>(resBank009, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 은행업권 - 대출상품계좌 거래내역 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/accounts/loan/transactions", produces = "application/json; charset=UTF-8")
	public ResponseEntity loanTransactions(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqBank010 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/loan/transactions");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResBank010 resBank010 = bankService.loanTransactions(req, api_id, own_org_cd);
			return new ResponseEntity<ResBank010>(resBank010, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 개인형 IRP 계좌 목록 조회 (은행, 금투, 보험 공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/irps", produces = "application/json; charset=UTF-8")
	public ResponseEntity irpAccounts(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqIRP001 req) {

		try {
			api_id = checkApiId(api_id, "/bank/irps");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResIRP001 resIRP001 = irpService.listAccount(req, api_id, own_org_cd);
			return new ResponseEntity<ResIRP001>(resIRP001, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 보험 공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/irps/basic", produces = "application/json; charset=UTF-8")
	public ResponseEntity irpBasic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqIRP002 req) {

		try {
			api_id = checkApiId(api_id, "/bank/irps/basic");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResIRP002 resIRP002 = irpService.irpBasic(req, api_id, own_org_cd);
			return new ResponseEntity<ResIRP002>(resIRP002, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/irps/detail", produces = "application/json; charset=UTF-8")
	public ResponseEntity irpDetail(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqIRP003 req) {

		try {
			api_id = checkApiId(api_id, "/bank/irps/detail");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResIRP003 resIRP003 = irpService.irpDetail(req, api_id, own_org_cd);
			return new ResponseEntity<ResIRP003>(resIRP003, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/irps/transactions", produces = "application/json; charset=UTF-8")
	public ResponseEntity irpTransactions(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@Valid @RequestBody ReqIRP004 req) {

		try {
			api_id = checkApiId(api_id, "/bank/irps/transactions");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResIRP004 resIRP004 = irpService.irpTransactions(req, api_id, own_org_cd);
			return new ResponseEntity<ResIRP004>(resIRP004, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * API 목록 조회 (공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping("/apis")
	public ResponseEntity apis(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCmn001 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/apis");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCmn001 resCmn001 = commonService.apis(req, api_id, own_org_cd);

			return new ResponseEntity<ResCmn001>(resCmn001, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 전송요구 내역 조회 (공통)
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping("/consents")
	public ResponseEntity consents(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCmn002 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/bank/consents");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCmn002 resCmn002 = commonService.consents(req, api_id, own_org_cd);

			return new ResponseEntity<ResCmn002>(resCmn002, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}
}
