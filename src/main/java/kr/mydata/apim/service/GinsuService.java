package kr.mydata.apim.service;

import kr.mydata.apim.vo.ginsu.*;

public interface GinsuService {
    /**
     * 보증보험업권 - 보증보험 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResGinsu001 listGinsu(ReqGinsu001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보증보험업권 - 보증보험 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResGinsu002 ginsuBasic(ReqGinsu002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보증보험업권 - 보증보험 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResGinsu003 ginsuTransactions(ReqGinsu003 req, String api_id, String own_org_cd) throws Exception;
}
