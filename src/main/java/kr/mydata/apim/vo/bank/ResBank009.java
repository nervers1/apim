package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

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
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate int_repay_date;
}
