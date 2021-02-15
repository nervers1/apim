package kr.mydata.apim.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
@Service
public class IRPServiceImpl implements IRPService {

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public IRPServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 개인형 IRP - 계좌 목록 조회(은행, 금투, 보험 공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 */
	@Override
	public ResIRP001 listAccount(ReqIRP001 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResIRP001.class);
	}

	/**
	 * 개인형 IRP - 계좌 기본정보 조회(은행, 금투, 보험 공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 */
	@Override
	public ResIRP002 irpBasic(ReqIRP002 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and ast_id = ? and org_cd = ? and own_org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), req.getAccount_num(),
				req.getOrg_code(), own_org_cd);

		log.info("res --> {}", res);

		return mapper.readValue(res, ResIRP002.class);
	}

	/**
	 * 개인형 IRP - 계좌 추가정보 조회(은행, 금투, 보험 공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 */
	@Override
	public ResIRP003 irpDetail(ReqIRP003 req, String api_id, String own_org_cd) throws JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code(), req.getAccount_num());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResIRP003.class);
	}

	/**
	 * 개인형 IRP - 계좌 거래내역 조회(은행, 금투, 보험 공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 */
	@Override
	public ResIRP004 irpTransactions(ReqIRP004 req, String api_id, String own_org_cd) throws JsonProcessingException {

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

		ResIRP004 resIRP004 = mapper.readValue(res, ResIRP004.class);

		if (null == resIRP004.getTrans_list() || 0 == resIRP004.getTrans_list().size()) {
			resIRP004.setRsp_code("99999");
			resIRP004.setRsp_msg("검색결과 없음");
		}

		return resIRP004;
	}
}
