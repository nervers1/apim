package kr.mydata.apim.service;

import kr.mydata.apim.vo.telecom.*;

public interface TelecomService {
    /**
     * 통신업권 - 통신 계약 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResTelecom001 listTelecom(ReqTelecom001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 통신업권 - 청구 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResTelecom002 telecomBills(ReqTelecom002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 통신업권 - 통신 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResTelecom003 telecomTransactions(ReqTelecom003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 통신업권 - 결제 내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResTelecom004 telecomPaidTransactions(ReqTelecom004 req, String api_id, String own_org_cd) throws Exception;
}
