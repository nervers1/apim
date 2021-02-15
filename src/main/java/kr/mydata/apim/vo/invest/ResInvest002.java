package kr.mydata.apim.vo.invest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 계좌 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String issue_date;
  private String is_tax_benefits;
  private BigDecimal withholdings_amt;
  private BigDecimal credit_loan_amt;
  private BigDecimal mortgage_amt;
  private String currency_code;
}
