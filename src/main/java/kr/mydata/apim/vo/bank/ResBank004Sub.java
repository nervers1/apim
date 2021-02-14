package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 수신계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank004Sub {
  private String trans_dtime;
  private String trans_no;
  private String trans_type;
  private String trans_class;
  private BigDecimal trans_amt;
  private BigDecimal balance_amt;
  private int paid_in_cnt;
}
