package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.efin.*;

public interface EfinService {

  /**
   * 전자금융 - 전자지급수단 목록 조회
   * @param req ReqEfin001
   * @param api_id String
   * @param own_org_cd String
   * @return ResEfin001
   * @throws JsonProcessingException e
   */
  ResEfin001 accounts(ReqEfin001 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 전자금융 - 전자지급수단 잔액정보 조회
   * @param req ReqEfin002
   * @param api_id String
   * @param own_org_cd String
   * @return ResEfin002
   * @throws JsonProcessingException e
   */
  ResEfin002 balance(ReqEfin002 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 전자금융 - 전자지급수단 자동충전정보 조회
   * @param req ReqEfin003
   * @param api_id String
   * @param own_org_cd String
   * @return ResEfin004
   * @throws JsonProcessingException e
   */
  ResEfin003 charge(ReqEfin003 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 전자금융 - 선불 거래내역 조회
   * @param req ReqEfin004
   * @param api_id String
   * @param own_org_cd String
   * @return ResEfin004
   * @throws JsonProcessingException e
   */
  ResEfin004 prepaidTransactions(ReqEfin004 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 전자금융 - 결제내역 조회
   * @param req ReqEfin005
   * @param api_id String
   * @param own_org_cd String
   * @return ResEfin005
   * @throws JsonProcessingException e
   */
  ResEfin005 transactions(ReqEfin005 req, String api_id, String own_org_cd) throws JsonProcessingException;
}
