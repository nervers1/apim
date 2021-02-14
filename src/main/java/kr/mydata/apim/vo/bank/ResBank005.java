package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 투자상품계좌 기본정보 조회 - 출력
 */
@Data
public class ResBank005 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String standard_fund_code;
  private String paid_in_type;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate exp_date;
}
