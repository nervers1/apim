package kr.mydata.apim.vo.invest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 계좌 상품정보 조회 - 상세
 */
@Data
public class ResInvest004Sub {
  private String prod_type;
  private String prod_type_detail;
  private String prod_code;
  private String prod_name;
  private BigDecimal purchase_amt;
  private BigDecimal holding_num;
  private BigDecimal avail_for_sale_num;
  private BigDecimal eval_amt;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  private BigDecimal paid_in_amt;
  private BigDecimal withdrawal_amt;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate last_paid_in_date;
  private BigDecimal received_amt;
  private String currency_code;
}
