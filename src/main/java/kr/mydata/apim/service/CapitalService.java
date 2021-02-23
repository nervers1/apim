package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.loan.*;

public interface CapitalService {
    /**
     * 할부금융 업권 - 계좌 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResLoan001 loans(ReqLoan001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResLoan002 basic(ReqLoan002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResLoan003 detail(ReqLoan003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResLoan004 transactions(ReqLoan004 req, String api_id, String own_org_cd) throws Exception;
}
