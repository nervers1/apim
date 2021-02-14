package kr.mydata.apim.service;

import kr.mydata.apim.vo.auth.*;

public interface AuthService {

  /**
   * 개별인증 API - 인가코드 발급 요청
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  ResAuth001 authorize(ReqAuth001 req, String api_id, String own_org_cd);
  ResAuth002 token(ReqAuth002 req, String api_id, String own_org_cd);
  ResAuth003 token(ReqAuth003 req, String api_id, String own_org_cd);
  void revoke(ReqAuth004 req, String api_id, String own_org_cd);
}
