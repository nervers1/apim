package kr.mydata.apim.vo.card;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 청구 기본정보 조회 - 상세
 */
@Data
public class ResCard004Sub {
  private String seqno;
  private BigDecimal charge_amt;
  private int charge_day;
  private int charge_month;
  private String paid_out_date;
  private String card_type;
}
