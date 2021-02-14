package kr.mydata.apim.vo.efin;

import lombok.Data;

import java.util.List;

/**
 * 전자지급수단 목록 조회 - 상세
 */
@Data
public class ResEpay001Sub {
  private String sub_key;
  private String account_id;
  private String is_consent;
  private String account_status;
  private String is_pay_reg;
  private int pay_cnt;
  private List<ResEpay001Sub2> pay_list;
}
