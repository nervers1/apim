package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 대출상품 추가정보 조회 - 출력
 */
@Data
public class ResInsu010 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String currency_code;
  private BigDecimal balance_amt;
  private BigDecimal loan_principal;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate next_int_repay_date;
}
