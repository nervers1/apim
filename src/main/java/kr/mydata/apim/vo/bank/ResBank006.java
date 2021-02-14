package kr.mydata.apim.vo.bank;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 투자상품계좌 추가정보 조회 - 출력
 */
@Data
public class ResBank006 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String currency_code;
  private BigDecimal balance_amt;
  private BigDecimal eval_amt;
  private BigDecimal inv_principal;
  private BigDecimal fund_num;
}
