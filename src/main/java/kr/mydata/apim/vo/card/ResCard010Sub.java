package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 리볼빙 정보 조회 - 상세
 */
@Data
public class ResCard010Sub {
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate req_date;
  private double min_pay_rate;
  private BigDecimal min_pay_amt;
  private double agreed_pay_rate;
  private BigDecimal remained_amt;
}
