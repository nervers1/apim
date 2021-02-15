package kr.mydata.apim.vo.invest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest003Sub {
  private String prod_name;
  private String prod_code;
  private String trans_dtime;
  private String trans_type;
  private String trans_type_detail;
  private int trans_num;
  private BigDecimal trans_amt;
  private BigDecimal settle_amt;
  private BigDecimal balance_amt;
  private String currency_code;
}
