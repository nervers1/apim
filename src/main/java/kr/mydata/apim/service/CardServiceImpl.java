package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.card.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

  final JdbcTemplate jdbcTemplate;

  public CardServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 카드업권 : 카드 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard001 listCard(ReqCard001 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 카드 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 포인트 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard003 cardPoint(ReqCard003 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 청구 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard004 cardBills(ReqCard004 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 청구 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard005 cardBillsDetail(ReqCard005 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 결제정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard006 cardPayment(ReqCard006 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 국내 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 해외 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard008 cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 대출상품 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard009 cardLoans(ReqCard009 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 리볼빙 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard010 cardLoansRevolving(ReqCard010 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 단기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard011 cardLoansShortTerm(ReqCard011 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 카드업권 : 장기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard012 cardLoansLongTerm(ReqCard012 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }
}
