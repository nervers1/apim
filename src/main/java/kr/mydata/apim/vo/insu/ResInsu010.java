package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 대출상품 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu010 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String currency_code;
  private BigDecimal balance_amt;
  private BigDecimal loan_principal;
  private String next_int_repay_date;
}
