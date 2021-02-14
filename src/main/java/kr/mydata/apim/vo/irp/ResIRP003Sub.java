package kr.mydata.apim.vo.irp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통) - 항목
 */
@Data
public class ResIRP003Sub {
  private String irp_name;
  private String irp_type;
  private BigDecimal eval_amt;
  private BigDecimal inv_principal;
  private int fund_num;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate open_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate exp_date;
  private Double int_rate;
}
