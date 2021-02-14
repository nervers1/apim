package kr.mydata.apim.vo.insu;


import lombok.Data;

import java.math.BigDecimal;

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
  private String pay_end_date;
  private BigDecimal pay_amt;
  private String currency_code;
  private String is_auto_pay;
}
