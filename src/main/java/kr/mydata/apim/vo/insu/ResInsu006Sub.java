package kr.mydata.apim.vo.insu;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 보험 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu006Sub {
  private String trans_date;
  private int trans_applied_month;
  private int trans_no;
  private BigDecimal paid_amt;
  private String currency_code;
  private String pay_method;
}
