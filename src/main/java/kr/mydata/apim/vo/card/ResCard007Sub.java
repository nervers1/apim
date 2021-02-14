package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 국내 승인내역 조회 - 상세
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCard007Sub {
  private String approved_num;
  private String status;
  private String pay_type;
  private String approved_dtime;
  private String cancel_dtime;
  private String merchant_name;
  private BigDecimal approved_amt;
  private int total_intall_cnt;
}
