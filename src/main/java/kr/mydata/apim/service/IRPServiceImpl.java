package kr.mydata.apim.service;

import kr.mydata.apim.vo.irp.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class IRPServiceImpl implements IRPService {

  /**
   * 개인형 IRP - 계좌 목록 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_api_id
   * @return
   */
  @Override
  public ResIRP001 listAccount(ReqIRP001 req, String api_id, String own_api_id) {
    return null;
  }

  /**
   * 개인형 IRP - 계좌 기본정보 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_api_id
   * @return
   */
  @Override
  public ResIRP002 irpBasic(ReqIRP002 req, String api_id, String own_api_id) {
    return null;
  }

  /**
   * 개인형 IRP - 계좌 추가정보 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_api_id
   * @return
   */
  @Override
  public ResIRP003 irpDetail(ReqIRP003 req, String api_id, String own_api_id) {
    return null;
  }

  /**
   * 개인형 IRP - 계좌 거래내역 조회(은행, 금투, 보험 공통)
   * @param req
   * @param api_id
   * @param own_api_id
   * @return
   */
  @Override
  public ResIRP004 irpTransactions(ReqIRP004 req, String api_id, String own_api_id) {
    return null;
  }
}
