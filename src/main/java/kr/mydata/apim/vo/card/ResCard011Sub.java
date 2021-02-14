package kr.mydata.apim.vo.card;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 단기대출 정보 조회 - 상세
 */
@Data
public class ResCard011Sub {
  private String loan_dtime;
  private BigDecimal loan_amt;
  private String pay_due_date;
  private int int_rate;
}
