package kr.mydata.apim.vo.insu;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 대출상품 거래내역 조회 - 상세
 */
@Data
public class ResInsu011Sub {
  private String trans_no;
  private String trans_dtime;
  private String currency_code;
  private BigDecimal loan_paid_amt;
  private BigDecimal int_paid_amt;
  private int int_cnt;
  private List<ResInsu011Sub2> int_list;
}
