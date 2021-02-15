package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 대출상품 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu011Sub {
  private String trans_no;
  private String trans_dtime;
  private String currency_code;
  private BigDecimal loan_paid_amt;
  private BigDecimal int_paid_amt;
  private int int_cnt;
  private List<ResInsu011Sub2> int_list;
}
