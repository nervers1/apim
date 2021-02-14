package kr.mydata.apim.vo.bank;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 투자상품계좌 거래내역 조회 - 상세
 */
@Data
public class ResBank007Sub {
  private String trans_dtime;
  private String trans_no;
  private String trans_type;
  private String currency_code;
  private BigDecimal base_amt;
  private BigDecimal trans_fund_num;
  private BigDecimal trans_amt;
  private BigDecimal balance_amt;
}
