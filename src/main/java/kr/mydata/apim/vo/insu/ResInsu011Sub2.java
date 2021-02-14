package kr.mydata.apim.vo.insu;

import lombok.Data;

/**
 * 대출상품 거래내역 조회 - 이자적용항목
 */
@Data
public class ResInsu011Sub2 {
  private String int_start_date;
  private String int_end_date;
  private double int_rate;
  private String int_type;
}
