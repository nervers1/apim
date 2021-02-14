package kr.mydata.apim.vo.card;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 국내 승인내역 조회 - 상세
 */
@Data
public class ResCard007Sub {
  private String approved_num;
  private String status;
  private String pay_type;
  private String approved_dtime;
  private String cancel_dtime;
  private String merchant_name;
  private BigDecimal approved_amt;
  private int total_intall_cnt;
}
