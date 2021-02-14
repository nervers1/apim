package kr.mydata.apim.vo.common;

import lombok.Data;

/**
 * 전송요구 내역 조회 (공통) - 출력
 */
@Data
public class ResCmn002 {
  private String rsp_code;
  private String rsp_msg;
  private String is_scheduled;
  private int cycle;
  private String end_date;
  private String purpose;
  private int period;
}
