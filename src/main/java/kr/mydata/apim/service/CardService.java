package kr.mydata.apim.service;

import kr.mydata.apim.vo.card.*;

public interface CardService {
    /**
     * 카드업권 : 카드 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard001 listCard(ReqCard001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 카드 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd, String card_id) throws Exception;

    /**
     * 카드업권 : 포인트 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard003 cardPoint(ReqCard003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 청구 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard004 cardBills(ReqCard004 req, String api_id, String own_org_cd) throws Exception, Exception;

    /**
     * 카드업권 : 청구 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard005 cardBillsDetail(ReqCard005 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 결제정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard006 cardPayment(ReqCard006 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 국내 승인내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd, String card_id) throws Exception;

    /**
     * 카드업권 : 해외 승인내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param card_id
     * @return
     * @throws Exception
     */
    ResCard008 cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd, String card_id) throws Exception;

    /**
     * 카드업권 : 대출상품 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard009 cardLoans(ReqCard009 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 리볼빙 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard010 cardLoansRevolving(ReqCard010 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 단기대출 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard011 cardLoansShortTerm(ReqCard011 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 카드업권 : 장기대출 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    ResCard012 cardLoansLongTerm(ReqCard012 req, String api_id, String own_org_cd) throws Exception;

}
