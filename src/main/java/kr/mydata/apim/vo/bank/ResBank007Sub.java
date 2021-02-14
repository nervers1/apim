package kr.mydata.apim.vo.bank;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
