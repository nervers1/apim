package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.bank.*;

public interface BankService {
    /**
     * 은행업권 : 계좌 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 수신계좌 기본정보 조회(은행)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     */
    ResBank002 inqBasicInfo(ReqBank002 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 수신계좌 추가정보 조회(은행)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return ResBank003
     * @throws JsonProcessingException
     */
    ResBank003 addtionalInfo(ReqBank003 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 수신계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     */
    ResBank004 listTransactions(ReqBank004 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 투자상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank005 investBasic(ReqBank005 req, String api_id, String own_org_cd) throws JsonProcessingException;


    /**
     * 은행업권 - 투자상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank006 investDetail(ReqBank006 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 투자상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank007 investTransactions(ReqBank007 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 대출상품계좌 기본정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank008 loanBasic(ReqBank008 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 대출상품계좌 추가정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank009 loanDetail(ReqBank009 req, String api_id, String own_org_cd) throws JsonProcessingException;

    /**
     * 은행업권 - 대출상품계좌 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws JsonProcessingException
     */
    ResBank010 loanTransactions(ReqBank010 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
