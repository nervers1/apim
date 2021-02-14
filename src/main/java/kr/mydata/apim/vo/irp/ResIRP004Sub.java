package kr.mydata.apim.vo.irp;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통) - 항목
 */
@Data
public class ResIRP004Sub {
  private String trans_dtime;
  private String trans_type;
  private BigDecimal trans_amt;
}
