package kr.mydata.apim.vo.irp;


import lombok.Data;

import java.math.BigDecimal;

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
  private String issue_date;
  private String first_deposit_date;
}
