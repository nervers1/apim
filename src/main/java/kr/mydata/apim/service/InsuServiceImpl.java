package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.vo.insu.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class InsuServiceImpl implements InsuService {
  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;

  public InsuServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 보험업권 - 보험 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu001 listInsu(ReqInsu001 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 보험 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu002 insuBasic(ReqInsu002 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 보험 특약정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu003 insuContracts(ReqInsu003 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 자동차보험 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu004 insuCar(ReqInsu004 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 보험 납입정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu005 insuPayment(ReqInsu005 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 보험 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu006 insuTransactions(ReqInsu006 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 자동차보험 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu007 insuCarTransactions(ReqInsu007 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 대출상품 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu008 insuLoans(ReqInsu008 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 대출상품 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu009 insuLoansBasic(ReqInsu009 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 대출상품 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu010 insuLoansDetail(ReqInsu010 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 보험업권 - 대출상품 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInsu011 insuLoansTransactions(ReqInsu011 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }
}
