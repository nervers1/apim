package kr.mydata.apim.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mydata.apim.service.CardService;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.vo.ErrorResponse;
import kr.mydata.apim.vo.card.ReqCard001;
import kr.mydata.apim.vo.card.ReqCard002;
import kr.mydata.apim.vo.card.ReqCard003;
import kr.mydata.apim.vo.card.ReqCard004;
import kr.mydata.apim.vo.card.ReqCard005;
import kr.mydata.apim.vo.card.ReqCard006;
import kr.mydata.apim.vo.card.ReqCard007;
import kr.mydata.apim.vo.card.ReqCard008;
import kr.mydata.apim.vo.card.ReqCard009;
import kr.mydata.apim.vo.card.ReqCard010;
import kr.mydata.apim.vo.card.ReqCard011;
import kr.mydata.apim.vo.card.ReqCard012;
import kr.mydata.apim.vo.card.ResCard001;
import kr.mydata.apim.vo.card.ResCard002;
import kr.mydata.apim.vo.card.ResCard003;
import kr.mydata.apim.vo.card.ResCard004;
import kr.mydata.apim.vo.card.ResCard005;
import kr.mydata.apim.vo.card.ResCard006;
import kr.mydata.apim.vo.card.ResCard007;
import kr.mydata.apim.vo.card.ResCard009;
import kr.mydata.apim.vo.card.ResCard010;
import kr.mydata.apim.vo.card.ResCard011;
import kr.mydata.apim.vo.card.ResCard012;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/card")
@SuppressWarnings("rawtypes")
public class CardController {

	@Autowired
	private CardService cardService;

	@Autowired
	private CommonService commonService;

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public CardController(JdbcTemplate jdbcTemplate) {
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
	 * 카드업권 - 카드 목록 조회
	 *
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/cards", produces = "application/json; charset=UTF-8")
	public ResponseEntity cards(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCard001 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/card/cards");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCard001 resCard001 = cardService.cards(req, api_id, own_org_cd);

			return new ResponseEntity<ResCard001>(resCard001, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 카드 기본정보 조회
	 *
	 * @param api_id
	 * @param own_org_cd
	 * @param card_id
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/cards/{card_id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity cardBasic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@PathVariable(value = "card_id") String card_id, @Valid ReqCard002 req) {

		log.info("api_id : {}", api_id);
		log.info("card_id : {}", card_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/card/cards/{card_id}");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCard002 resCard002 = cardService.cardBasic(req, api_id, own_org_cd, card_id);

			return new ResponseEntity<ResCard002>(resCard002, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 : 포인트 정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/cards/point", produces = "application/json; charset=UTF-8")
	public ResponseEntity point(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCard003 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/card/cards/point");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCard003 res = cardService.point(req, api_id, own_org_cd);

			return new ResponseEntity<ResCard003>(res, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 : 청구 기본정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/cards/bills", produces = "application/json; charset=UTF-8")
	public ResponseEntity bills(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard004 req) {
		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);

		try {
			ResCard004 res = cardService.bills(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 : 청구 추가정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/cards/bills/detail", produces = "application/json; charset=UTF-8")
	public ResponseEntity billsDetail(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard005 req) {
		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);

		try {
			ResCard005 res = cardService.billsDetail(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 : 결제정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/cards/payment", produces = "application/json; charset=UTF-8")
	public ResponseEntity payment(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard006 req) {
		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		try {
			ResCard006 res = cardService.payment(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 국내 승인내역 조회
	 *
	 * @param api_id
	 * @param req
	 * @return http://localhost:8080/v1/card/cards/10456243512223/approval-domestic?org_code=0000000&from_dtime=20210101000000&to_dtime=20210501235959&limit=20
	 */
	@GetMapping(value = "/cards/{card_id}/approval-domestic", produces = "application/json; charset=UTF-8")
	public ResponseEntity cardApprovalDomestic(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@PathVariable(value = "card_id") String card_id, @Valid ReqCard007 req) {

		log.info("api_id : {}", api_id);
		log.info("card_id : {}", card_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			api_id = checkApiId(api_id, "/card/cards/{card_id}/approval-domestic");
			own_org_cd = checkOwnOrgCd(own_org_cd, authorization);

			ResCard007 res = cardService.cardApprovalDomestic(req, api_id, own_org_cd, card_id);

			return new ResponseEntity<ResCard007>(res, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 해외 승인내역 조회
	 *
	 * @param api_id
	 * @param req
	 * @return http://localhost:8080/v1/card/cards/10456243512223/approval-domestic?org_code=0000000&from_dtime=20210101000000&to_dtime=20210501235959&limit=20
	 */
	@GetMapping(value = "/cards/{card_id}/approval-overseas", produces = "application/json; charset=UTF-8")
	public ResponseEntity cardApprovalOverseas(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd,
			@PathVariable(value = "card_id") String card_id, ReqCard008 req) {

		log.info("api_id : {}", api_id);
		log.info("card_id : {}", card_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			String res = cardService.cardApprovalOverseas(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 대출상품 목록 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/loans", produces = "application/json; charset=UTF-8")
	public ResponseEntity loans(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard009 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			ResCard009 res = cardService.loans(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}

	}

	/**
	 * 카드업권 - 리볼빙 정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/loans/revolving", produces = "application/json; charset=UTF-8")
	public ResponseEntity loansRevolving(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard010 req) {
		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);
		try {
			ResCard010 res = cardService.loansRevolving(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 단기대출 정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/loans/short-term", produces = "application/json; charset=UTF-8")
	public ResponseEntity loansShortTerm(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard011 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			ResCard011 res = cardService.loansShortTerm(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);

		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}

	/**
	 * 카드업권 - 장기대출 정보 조회
	 * 
	 * @param api_id
	 * @param own_org_cd
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/loans/long-term", produces = "application/json; charset=UTF-8")
	public ResponseEntity loansLongTerm(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, ReqCard012 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			ResCard012 res = cardService.loansLongTerm(req, api_id, own_org_cd);
			return new ResponseEntity(res, HttpStatus.OK);

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
	public ResponseEntity apis(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCmn001 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
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
	public ResponseEntity consents(@RequestHeader(value = "x-api-id", required = false) String api_id,
			@RequestHeader(value = "x-own-org-cd", required = false) String own_org_cd, @Valid ReqCmn002 req) {

		log.info("api_id : {}", api_id);
		log.info("own_org_cd : {}", own_org_cd);
		log.info("req : {}", req);

		try {
			ResCmn002 resCmn002 = commonService.consents(req, api_id, own_org_cd);

			return new ResponseEntity<ResCmn002>(resCmn002, HttpStatus.OK);
		} catch (Exception e) {
			return returnErrorMessage(e);
		}
	}
}
