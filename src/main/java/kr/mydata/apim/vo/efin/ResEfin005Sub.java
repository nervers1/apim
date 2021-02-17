package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 결제내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin005Sub {
  private String trans_type;
  private String fob_name;
  private String trans_num;
  private String trans_dtime;
  private BigDecimal trans_amt;
  private String trans_org_code;
  private String trans_id;
  private int total_install_cnt;
  private String merchant_name;
  private String trans_title;
  private String pay_method;
}
