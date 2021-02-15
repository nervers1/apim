package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;

public interface CommonService {
	ResCmn001 apis(ReqCmn001 req, String api_id, String own_org_cd)
			throws JsonMappingException, JsonProcessingException;

	ResCmn002 consents(ReqCmn002 req, String api_id, String own_org_cd)
			throws JsonMappingException, JsonProcessingException;
}
