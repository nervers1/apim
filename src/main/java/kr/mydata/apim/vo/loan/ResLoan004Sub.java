package kr.mydata.apim.vo.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 대출상품계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResLoan004Sub {
  private String trans_dtime;
  private String trans_no;
  private String trans_type;
  private BigDecimal trans_amt;
  private BigDecimal balance_amt;
  private BigDecimal trans_principal_amt;
  private BigDecimal trans_int_amt;
  private int int_cnt;
  private List<ResLoan004Sub2> int_list;
}
