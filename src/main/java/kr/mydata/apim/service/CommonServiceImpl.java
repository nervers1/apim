package kr.mydata.apim.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mydata.apim.mapper.common.ResCmn001Mapper;
import kr.mydata.apim.vo.bank.ResBank001;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CommonServiceImpl implements CommonService {

	private final ObjectMapper mapper = new ObjectMapper();
	private final JdbcTemplate jdbcTemplate;

	public CommonServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * API 목록 조회 (공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@Override
	public ResCmn001 apis(ReqCmn001 req, String api_id, String own_org_cd)
			throws JsonMappingException, JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResCmn001.class);
	}

	/**
	 * 전송요구 내역 조회(공통)
	 * 
	 * @param req
	 * @param api_id
	 * @param own_org_cd
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@Override
	public ResCmn002 consents(ReqCmn002 req, String api_id, String own_org_cd)
			throws JsonMappingException, JsonProcessingException {

		String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
		String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd,
				req.getOrg_code());

		log.info("res --> {}", res);

		return mapper.readValue(res, ResCmn002.class);
	}
}
