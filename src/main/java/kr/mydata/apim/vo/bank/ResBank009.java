package kr.mydata.apim.vo.bank;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 대출상품계좌 추가정보 조회 - 출력
 */
@Data
public class ResBank009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private BigDecimal balance_amt;
  private BigDecimal loan_principal;
  private String int_repay_date;
}
