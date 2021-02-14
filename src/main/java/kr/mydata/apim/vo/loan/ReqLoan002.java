package kr.mydata.apim.vo.loan;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 대출상품계좌 기본정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqLoan002 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String search_timestamp;
}
