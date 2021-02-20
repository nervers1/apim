package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 전자지급수단 잔액정보 조회 - 상세(권명정보)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin002Sub {
    private String fob_name;
    private BigDecimal total_balance_amt;
    private BigDecimal charge_balance_amt;
    private BigDecimal reserve_balance_amt;
    private BigDecimal reserve_due_amt;
    private BigDecimal exp_due_amt;
    private int limit_amt;
}
