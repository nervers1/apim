package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 수신계좌 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private BigDecimal current_balance_amt;
    private BigDecimal withdrawable_amt;
    private double offered_rate;
    private int last_paid_in_cnt;
}
