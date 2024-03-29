package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.card.*;

public interface CardService {
  /**
   * 카드업권 : 카드 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard001 cards(ReqCard001 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 카드 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param card_id
   * @return
   * @throws JsonProcessingException
   */
  ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd, String card_id) throws JsonProcessingException;

  /**
   * 카드업권 : 포인트 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard003 point(ReqCard003 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 청구 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard004 bills(ReqCard004 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 청구 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard005 billsDetail(ReqCard005 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 결제정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard006 payment(ReqCard006 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 국내 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param card_id
   * @return
   * @throws JsonProcessingException
   */
  ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd, String card_id) throws JsonProcessingException;

  /**
   * 카드업권 : 해외 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  String cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 대출상품 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard009 loans(ReqCard009 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 리볼빙 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard010 loansRevolving(ReqCard010 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 단기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard011 loansShortTerm(ReqCard011 req, String api_id, String own_org_cd) throws JsonProcessingException;

  /**
   * 카드업권 : 장기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  ResCard012 loansLongTerm(ReqCard012 req, String api_id, String own_org_cd) throws JsonProcessingException;

}
