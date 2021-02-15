package kr.mydata.apim.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BankServiceImpl implements BankService {

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public BankServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 은행업권 : 계좌 목록 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResBank001.class);
	}

	/**
	 * 은행업권 - 수신계좌 기본정보 조회(은행)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank002 inqBasicInfo(ReqBank002 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and ast_id = ? and org_cd = ? and own_org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), req.getAccount_num(),
				req.getOrg_code(), own_org_cd);

		log.info("res --> {}", res);

		return mapper.readValue(res, ResBank002.class);
	}

	/**
	 * 은행업권 - 수신계좌 추가정보 조회(은행)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank003 addtionalInfo(ReqBank003 req, String api_id, String own_org_cd) throws JsonProcessingException {
		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResBank003.class);
	}

	/**
	 * 은행업권 - 수신계좌 거래내역 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank004 listTransactions(ReqBank004 req, String api_id, String own_org_cd)
			throws JsonProcessingException {

		int nextPage = req.getNext_page() == null ? 0 : Integer.valueOf(req.getNext_page());
		int limit = Integer.valueOf(req.getLimit());

		//@formatter:off
		String sql = "select to_json(rslt) from (select"
				+ "'00000' as rsp_code,"
				+ "'정상' as rsp_msg,"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (max(rownum) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then 0::text\r\n"
				+ "  end as next_page,\r\n"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (count(*) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then count(*)::text\r\n"
				+ "  end as trans_cnt,\r\n"
				+ "       json_agg(res.list) as trans_list\r\n"
				+ "  from ( select  \r\n"
				+ "               ( row_number() over( order by a.list ->> 'trans_dtime' desc)) as rownum, *\r\n"
				+ "  from ( select  \r\n"
				+ "              jsonb_array_elements(res_data -> 'trans_list') as list\r\n"
				+ "  from tb_test_data \r\n"
				+ "where api_id = " + Integer.valueOf(api_id) + "\r\n"
				+ "  and org_cd = '" + req.getOrg_code() + "'\r\n"
				+ "  and own_org_cd = '" + own_org_cd + "'\r\n"
				+ "  and ast_id = '" + req.getAccount_num() + "') a \r\n"
				+ "where a.list ->> 'trans_dtime' between '" + req.getFrom_dtime() + "' and '" + req.getTo_dtime() + "') res\r\n"
				+ "  where res.rownum > " + nextPage + "\r\n"
				+ "   and res.rownum <= " + nextPage + " + " + limit + " + 1) rslt";
		//@formatter:on

		String res = jdbcTemplate.queryForObject(sql, String.class);

		log.info("res --> {}", res);

		ResBank004 resBank004 = mapper.readValue(res, ResBank004.class);

		if (null == resBank004.getTrans_list() || 0 == resBank004.getTrans_list().size()) {
			resBank004.setRsp_code("99999");
			resBank004.setRsp_msg("검색결과 없음");
		}

		return resBank004;
	}

	/**
	 * 은행업권 - 투자상품계좌 기본정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank005 investBasic(ReqBank005 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResBank005.class);
	}

	/**
	 * 은행업권 - 투자상품계좌 추가정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank006 investDetail(ReqBank006 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResBank006.class);
	}

	/**
	 * 은행업권 - 투자상품계좌 거래내역 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank007 investTransactions(ReqBank007 req, String api_id, String own_org_cd)
			throws JsonProcessingException {

		int nextPage = req.getNext_page() == null ? 0 : Integer.valueOf(req.getNext_page());
		int limit = Integer.valueOf(req.getLimit());

		//@formatter:off
		String sql = "select to_json(rslt) from (select"
				+ "'00000' as rsp_code,"
				+ "'정상' as rsp_msg,"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (max(rownum) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then 0::text\r\n"
				+ "  end as next_page,\r\n"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (count(*) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then count(*)::text\r\n"
				+ "  end as trans_cnt,\r\n"
				+ "       json_agg(res.list) as trans_list\r\n"
				+ "  from ( select  \r\n"
				+ "               ( row_number() over( order by a.list ->> 'trans_dtime' desc)) as rownum, *\r\n"
				+ "  from ( select  \r\n"
				+ "              jsonb_array_elements(res_data -> 'trans_list') as list\r\n"
				+ "  from tb_test_data \r\n"
				+ "where api_id = " + Integer.valueOf(api_id) + "\r\n"
				+ "  and org_cd = '" + req.getOrg_code() + "'\r\n"
				+ "  and own_org_cd = '" + own_org_cd + "'\r\n"
				+ "  and ast_id = '" + req.getAccount_num() + "') a \r\n"
				+ "where a.list ->> 'trans_dtime' between '" + req.getFrom_dtime() + "' and '" + req.getTo_dtime() + "') res\r\n"
				+ "  where res.rownum > " + nextPage + "\r\n"
				+ "   and res.rownum <= " + nextPage + " + " + limit + " + 1) rslt";
		//@formatter:on

		String res = jdbcTemplate.queryForObject(sql, String.class);

		log.info("res --> {}", res);

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		ResBank007 resBank007 = mapper.readValue(res, ResBank007.class);

		if (null == resBank007.getTrans_list() || 0 == resBank007.getTrans_list().size()) {
			resBank007.setRsp_code("99999");
			resBank007.setRsp_msg("검색결과 없음");
		}

		return resBank007;

	}

	/**
	 * 은행업권 - 대출상품계좌 기본정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank008 loanBasic(ReqBank008 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResBank008.class);
	}

	/**
	 * 은행업권 - 대출상품계좌 추가정보 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank009 loanDetail(ReqBank009 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return mapper.readValue(res, ResBank009.class);
	}

	/**
	 * 은행업권 - 대출상품계좌 거래내역 조회
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public ResBank010 loanTransactions(ReqBank010 req, String api_id, String own_org_cd)
			throws JsonProcessingException {

		int nextPage = req.getNext_page() == null ? 0 : Integer.valueOf(req.getNext_page());
		int limit = Integer.valueOf(req.getLimit());

		//@formatter:off
		String sql = "select to_json(rslt) from (select"
				+ "'00000' as rsp_code,"
				+ "'정상' as rsp_msg,"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (max(rownum) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then 0::text\r\n"
				+ "  end as next_page,\r\n"
				+ "  case\r\n"
				+ "    when max(rownum) = " + nextPage + " + " + limit + " + 1 then (count(*) - 1)::text\r\n"
				+ "    when max(rownum) <= " + nextPage + " + " + limit + " then count(*)::text\r\n"
				+ "  end as trans_cnt,\r\n"
				+ "       json_agg(res.list) as trans_list\r\n"
				+ "  from ( select  \r\n"
				+ "               ( row_number() over( order by a.list ->> 'trans_dtime' desc)) as rownum, *\r\n"
				+ "  from ( select  \r\n"
				+ "              jsonb_array_elements(res_data -> 'trans_list') as list\r\n"
				+ "  from tb_test_data \r\n"
				+ "where api_id = " + Integer.valueOf(api_id) + "\r\n"
				+ "  and org_cd = '" + req.getOrg_code() + "'\r\n"
				+ "  and own_org_cd = '" + own_org_cd + "'\r\n"
				+ "  and ast_id = '" + req.getAccount_num() + "') a \r\n"
				+ "where a.list ->> 'trans_dtime' between '" + req.getFrom_dtime() + "' and '" + req.getTo_dtime() + "') res\r\n"
				+ "  where res.rownum > " + nextPage + "\r\n"
				+ "   and res.rownum <= " + nextPage + " + " + limit + " + 1) rslt";
		//@formatter:on

		String res = jdbcTemplate.queryForObject(sql, String.class);

		log.info("res --> {}", res);

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		ResBank010 resBank010 = mapper.readValue(res, ResBank010.class);

		if (null == resBank010.getTrans_list() || 0 == resBank010.getTrans_list().size()) {
			resBank010.setRsp_code("99999");
			resBank010.setRsp_msg("검색결과 없음");
		}

		return resBank010;
	}
}
