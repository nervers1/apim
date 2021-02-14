package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 단기대출 정보 조회 - 상세
 */
@Data
public class ResCard011Sub {
  private String loan_dtime;
  private BigDecimal loan_amt;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate pay_due_date;
  private int int_rate;
}
