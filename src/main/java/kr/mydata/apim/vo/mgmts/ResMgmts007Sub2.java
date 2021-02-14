package kr.mydata.apim.vo.mgmts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 정보주체 별 전송요구 내역 조회 - 전송요구 항목
 */
@Data
public class ResMgmts007Sub2 {
  private String consent_type;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate consent_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate consent_exp_date;
}
