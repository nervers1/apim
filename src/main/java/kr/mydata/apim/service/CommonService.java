package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import kr.mydata.apim.vo.loan.ReqLoan001;
import kr.mydata.apim.vo.loan.ResLoan001;

public interface CommonService {

  ResCmn001 listAPI(ReqCmn001 req, String api_id, String own_org_cd) throws JsonProcessingException;
  ResCmn002 listConsents(ReqCmn002 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
