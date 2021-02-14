package kr.mydata.apim.vo.loan;

import lombok.Data;

/**
 * 계좌 목록 조회 - 상세
 */
@Data
public class ResLoan001Sub {
  private String account_num;
  private String is_consent;
  private String prod_name;
  private String account_type;
  private String account_status;
}
