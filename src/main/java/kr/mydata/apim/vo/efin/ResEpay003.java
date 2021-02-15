package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전자지급수단 자동충전정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEpay003 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String charge_org_code;
  private String charge_account_num;
  private String charge_option;
  private String charge_day;
  private int charge_base_amt;
  private int charge_amt;
}
