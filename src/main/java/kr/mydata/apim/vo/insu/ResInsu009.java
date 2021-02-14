package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 대출상품 기본정보 조회 - 출력
 */
@Data
public class ResInsu009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate loan_start_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate loan_exp_date;
}
