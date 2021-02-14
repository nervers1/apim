package kr.mydata.apim.vo.card;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class ResCard002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String card_type;
  private boolean is_trans_payable;
  private boolean is_cash_card;
  private String linked_bank_code;
  private String bank_brand;
  private BigDecimal annual_fee;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
}
