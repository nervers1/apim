package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 결제정보 조회 - 상세
 */
@Data
public class ResCard006Sub {
  private int seqno;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate pay_due_date;
  private BigDecimal pay_amt;
}
