package kr.mydata.apim.service;

import kr.mydata.apim.vo.invest.*;

public interface InvestService {
    /**
     * 금융투자 업권 : 계좌 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInvest001 listAccount(ReqInvest001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 금융투자 업권 : 계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInvest002 listBasic(ReqInvest002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 금융투자 업권 : 계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInvest003 listTransactions(ReqInvest003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 금융투자 업권 : 계좌 상품정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInvest004 listProducts(ReqInvest004 req, String api_id, String own_org_cd) throws Exception;


}
