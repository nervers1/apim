package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.bank.*;

public interface BankService {
  /**
   * 은행업권 : 계좌 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResBank001 listAccount(ReqBank001 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   *
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  ResBank002 inqBasicInfo(ReqBank002 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 수신계좌 추가정보 조회(은행)
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return ResBank003
   * @throws JsonProcessingException
   */
  ResBank003 addtionalInfo(ReqBank003 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 수신계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   */
  ResBank004 listTransactions(ReqBank004 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
