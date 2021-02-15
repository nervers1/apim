package kr.mydata.apim.vo.irp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통) - 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP004Sub {
  private String trans_dtime;
  private String trans_type;
  private BigDecimal trans_amt;
}
