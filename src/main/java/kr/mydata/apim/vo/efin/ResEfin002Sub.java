package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전자지급수단 잔액정보 조회 - 상세(권명정보)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin002Sub {
    private String fob_name;
    private String total_balance_amt;
    private String charge_balance_amt;
    private String reserve_balance_amt;
    private String reserve_due_amt;
    private String exp_due_amt;
    private String limit_amt;
}
