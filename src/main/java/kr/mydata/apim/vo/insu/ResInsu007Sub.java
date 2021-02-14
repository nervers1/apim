package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 자동차보험 거래내역 조회 - 상세
 */
@Data
public class ResInsu007Sub {
  private BigDecimal face_amt;
  private int trans_no;
  private BigDecimal paid_amt;
  private String pay_method;
}
