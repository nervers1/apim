package kr.mydata.apim.service;

import kr.mydata.apim.vo.mgmts.*;

public interface MgmtService {
    /**
     * 지원 API - 접근토큰 발급
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts001 supportToken(ReqMgmts001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 기관정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts002 orgs(ReqMgmts002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 서비스정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts003 services(ReqMgmts003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 통계자료 전송
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts004 statistics(ReqMgmts004 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 통합인증 API 호출용 자격증명 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts005 caCredentials(ReqMgmts005 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 통계자료 전송
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts101 companyToken(ReqMgmts101 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 데이터보유자 상태 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts102 status(ReqMgmts102 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 정보주체 별 전송요구 내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts103 consents(ReqMgmts103 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 지원 API - 통계자료 재전송 요청
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResMgmts104 reqStatistics(ReqMgmts104 req, String api_id, String own_org_cd) throws Exception;
}
