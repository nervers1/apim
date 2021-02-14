package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * 보험 기본정보 조회 - 출력
 */
@Data
public class ResInsu002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private boolean is_renewable;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate exp_date;
  private BigDecimal face_amt;
  private String currency_code;
  private boolean is_variable;
  private boolean is_universal;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate pension_rcv_start_date;
  private String pension_rcv_cycle;
  private boolean is_loanable;
  private int insured_count;
  private List<ResInsu002Sub> insured_list;
}
