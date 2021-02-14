package kr.mydata.apim.vo.irp;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 모험 공통) - 출력
 */
@Data
public class ResIRP002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private BigDecimal accumulated_amt;
  private BigDecimal eval_amt;
  private BigDecimal accumulated_employer_amt;
  private BigDecimal accumulated_employee_amt;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate issue_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate first_deposit_date;
}
