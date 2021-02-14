package kr.mydata.apim.vo.invest;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 계좌 거래내역 조회 - 상세
 */
@Data
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
