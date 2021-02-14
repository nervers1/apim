package kr.mydata.apim.vo.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 전송요구 내역 조회 (공통) - 출력
 */
@Data
public class ResCmn002 {
  private String rsp_code;
  private String rsp_msg;
  private boolean is_scheduled;
  private int cycle;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate end_date;
  private String purpose;
  private int period;
}
