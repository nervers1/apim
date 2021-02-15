package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 선불 거래내역 조회 - 거래내역 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEpay004Sub {
  private String trans_type;
  private String fob_name;
  private String trans_dtime;
  private BigDecimal trans_amt;
  private BigDecimal balance_amt;
  private String trans_org_code;
  private String trans_id;
  private String trans_memo;
}
