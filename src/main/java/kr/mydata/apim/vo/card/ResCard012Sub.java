package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 장기대출 정보 조회 - 상세
 */
@Data
public class ResCard012Sub {
  private String loan_dtime;
  private int loan_cnt;
  private String loan_type;
  private String loan_name;
  private BigDecimal loan_amt;
  private int int_rate;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate exp_date;
  private BigDecimal balance_amt;
  private String repay_method;
  private BigDecimal int_amt;
}
