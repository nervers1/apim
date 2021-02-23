package kr.mydata.apim.service;

import kr.mydata.apim.vo.insu.*;

public interface InsuService {
    /**
     * 보험업권 - 보험 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu001 listInsu(ReqInsu001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 보험 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu002 insuBasic(ReqInsu002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 보험 특약정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu003 insuContracts(ReqInsu003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 자동차보험 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu004 insuCar(ReqInsu004 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 보험 납입정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu005 insuPayment(ReqInsu005 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 보험 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu006 insuTransactions(ReqInsu006 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 자동차보험 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu007 insuCarTransactions(ReqInsu007 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 대출상품 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu008 insuLoans(ReqInsu008 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 대출상품 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu009 insuLoansBasic(ReqInsu009 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 대출상품 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu010 insuLoansDetail(ReqInsu010 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 보험업권 - 대출상품 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResInsu011 insuLoansTransactions(ReqInsu011 req, String api_id, String own_org_cd) throws Exception;
}
