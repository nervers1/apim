package kr.mydata.apim.vo.loan;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 계좌 목록 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqLoan001 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
