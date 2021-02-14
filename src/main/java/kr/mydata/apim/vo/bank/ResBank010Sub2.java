package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;

/**
 * 대출상품계좌 거래내역 조회 - 이자적용 Entity
 */
@Data
public class ResBank010Sub2 {
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate int_start_date;        // 이자적용시작일
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate int_end_date;          // 이자적용종료일
  private Double int_rate;                 // 적용이율
  private String int_type;                 // 이자종류(코드)
}
