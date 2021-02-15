package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 자동차보험 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu007Sub {
  private BigDecimal face_amt;
  private int trans_no;
  private BigDecimal paid_amt;
  private String pay_method;
}
