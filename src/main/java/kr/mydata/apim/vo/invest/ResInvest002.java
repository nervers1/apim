package kr.mydata.apim.vo.invest;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 계좌 기본정보 조회 - 출력
 */
@Data
public class ResInvest002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  private boolean is_tax_benefits;
  private BigDecimal withholdings_amt;
  private BigDecimal credit_loan_amt;
  private BigDecimal mortgage_amt;
  private String currency_code;
}
