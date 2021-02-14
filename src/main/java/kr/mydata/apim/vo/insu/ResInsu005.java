package kr.mydata.apim.vo.insu;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 보험 납입정보 조회 - 출력
 */
@Data
public class ResInsu005 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String pay_due;
  private String pay_cycle;
  private int pay_cnt;
  private String pay_org_code;
  private String pay_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate pay_end_date;
  private BigDecimal pay_amt;
  private String currency_code;
  private boolean is_auto_pay;
}
