package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 수신계좌 추가정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank003Sub {

    private String currency_code;
    private String balance_amt;
    private String withdrawable_amt;
    private String offered_rate;
    private String last_paid_in_cnt;
}
