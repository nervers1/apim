package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 대출상품 추가정보 조회 - 이자적용항목
 */
@Data
public class ResInsu011Sub2 {
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate int_start_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate int_end_date;
  private double int_rate;
  private String int_type;
}
