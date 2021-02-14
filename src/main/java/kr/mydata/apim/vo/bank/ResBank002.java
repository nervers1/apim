package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 수신계좌 기본정보 조회 - 출력
 */
@Data
public class ResBank002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String saving_mehotd;
  private String holder_name;
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate exp_date;
  private String currency_code;
  private BigDecimal commit_amt;
  private BigDecimal monthly_paid_in_amt;
  private BigDecimal termination_amt;
  private double last_offered_rate;
}
