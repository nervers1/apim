package kr.mydata.apim.vo.card;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 청구 추가정보 조회 - 상세
 */
@Data
public class ResCard005Sub {
  private String card_id;
  private String paid_dtime;
  private BigDecimal paid_amt;
  private String currency_code;
  private String merchant_name;
  private BigDecimal credit_fee_amt;
  private int total_install_cnt;
  private int cur_install_cnt;
  private BigDecimal balance_amt;
  private String prod_type;
}
