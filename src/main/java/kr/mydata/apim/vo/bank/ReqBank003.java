package kr.mydata.apim.vo.bank;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 수신계좌 추가정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqBank003 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private int seqno;
  private String currency_code;
  private String search_timestamp;
}
