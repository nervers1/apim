package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.mgmts.*;
import kr.mydata.apim.vo.oauth.ReqOAuth001;
import kr.mydata.apim.vo.oauth.ResOAuth001;

public interface MgmtService {
  /**
   * 지원 API - 접근토큰 발급
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResOAuth001 token(ReqOAuth001 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 기관정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts002 orgs(ReqMgmts002 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 서비스정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts003 services(ReqMgmts003 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 통계자료 전송
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts004 statistics(ReqMgmts004 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 데이터보유자 상태 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts006 status(ReqMgmts006 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 정보주체 별 전송요구 내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts007 consents(ReqMgmts007 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 지원 API - 통계자료 재전송 요청
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResMgmts008 reqStatistics(ReqMgmts008 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
