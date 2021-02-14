package kr.mydata.apim.vo.irp;

import lombok.Data;

import java.math.BigDecimal;

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
  private String open_date;
  private String exp_date;
  private Double int_rate;
}
