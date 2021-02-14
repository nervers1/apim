package kr.mydata.apim.vo.card;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 해외 승인내역 조회 - 상세
 */
@Data
public class ResCard008Sub {
  private String approved_num;
  private String status;
  private String pay_type;
  private String approved_dtime;
  private String cancel_dtime;
  private String merchant_name;
  private BigDecimal approved_amt;
  private String country_code;
  private String currency_code;
  private BigDecimal krw_amt;
}
