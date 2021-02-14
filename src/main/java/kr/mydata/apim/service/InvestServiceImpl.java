package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.mydata.apim.vo.invest.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class InvestServiceImpl implements InvestService {
  /**
   * 금융투자 업권 : 계좌 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInvest001 listAccount(ReqInvest001 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 금융투자 업권 : 계좌 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInvest002 listBasic(ReqInvest002 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 금융투자 업권 : 계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInvest003 listTransactions(ReqInvest003 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }

  /**
   * 금융투자 업권 : 계좌 상품정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResInvest004 listProducts(ReqInvest004 req, String api_id, String own_org_cd) throws JsonProcessingException {
    return null;
  }
}
