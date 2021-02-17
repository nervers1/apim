package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.auth.*;

public interface AuthService {

  /**
   * 개별인증 API - 인가코드 발급 요청
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  ResAuth001 authorize(ReqAuth001 req, String api_id, String own_org_cd) throws JsonProcessingException;
  ResAuth002 token(ReqAuth002 req, String api_id, String own_org_cd) throws JsonProcessingException;
  ResAuth003 token(ReqAuth003 req, String api_id, String own_org_cd) throws JsonProcessingException;
  void revoke(ReqAuth004 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
