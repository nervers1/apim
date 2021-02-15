package kr.mydata.apim.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CardServiceImpl implements CardService {

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public CardServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 카드업권 : 카드 목록 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard001 cards(ReqCard001 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResCard001.class);
	}

	/**
	 * 카드업권 : 카드 기본정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @param card_id
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd, String card_id)
			throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and ast_id = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, card_id,
				req.getOrg_code());

		log.info("res --> {}", res);

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResCard002.class);
	}

	/**
	 * 카드업권 : 포인트 정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard003 cardPoint(ReqCard003 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResCard003.class);
	}

	/**
	 * 카드업권 : 청구 기본정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard004 cardBills(ReqCard004 req, String api_id, String own_org_cd) throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 청구 추가정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard005 cardBillsDetail(ReqCard005 req, String api_id, String own_org_cd) throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 결제정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard006 cardPayment(ReqCard006 req, String api_id, String own_org_cd) throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 국내 승인내역 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @param card_id
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd, String card_id)
			throws JsonProcessingException {
		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
//    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id;
		// Card
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), card_id);
		log.info("res : {}", res);
		// to JSON
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		ResCard007 resCard007 = mapper.readValue(res, ResCard007.class);
		return resCard007;
	}

	/**
	 * 카드업권 : 해외 승인내역 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public String cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd)
			throws JsonProcessingException {
		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id;
		// Card
		String res = jdbcTemplate.queryForObject(sql, String.class);
		log.info("res : {}", res);
		// to JSON
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		return res;
	}

	/**
	 * 카드업권 : 대출상품 목록 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard009 cardLoans(ReqCard009 req, String api_id, String own_org_cd) throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 리볼빙 정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard010 cardLoansRevolving(ReqCard010 req, String api_id, String own_org_cd)
			throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 단기대출 정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard011 cardLoansShortTerm(ReqCard011 req, String api_id, String own_org_cd)
			throws JsonProcessingException {
		return null;
	}

	/**
	 * 카드업권 : 장기대출 정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResCard012 cardLoansLongTerm(ReqCard012 req, String api_id, String own_org_cd)
			throws JsonProcessingException {
		return null;
	}
}
