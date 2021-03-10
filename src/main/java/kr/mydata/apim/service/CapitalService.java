package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.capital.*;

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
    ResCapital001 loans(ReqCapital001 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResCapital002 basic(ReqCapital002 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResCapital003 detail(ReqCapital003 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 대출상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResCapital004 transactions(ReqCapital004 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 운용리스 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResCapital005 basicOplease(ReqCapital005 req, String api_id, String own_org_cd) throws Exception;

    /**
     * 할부금융 업권 - 운용리스 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResCapital006 transactionsOplease(ReqCapital006 req, String api_id, String own_org_cd) throws Exception;
}
