package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 보험 특약정보 조회 - 상세
 */
@Data
public class ResInsu003Sub {
  private String contract_name;
  private String contract_status;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate contract_exp_date;
  private BigDecimal contract_face_amt;
  private String currency_code;
  private boolean is_required;
}
