package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 수신계좌 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String saving_method;
    private String holder_name;
    private String issue_date;
    private String exp_date;
    private String currency_code;
    private BigDecimal commit_amt;
    private BigDecimal monthly_paid_in_amt;
    private BigDecimal termination_amt;
    private double last_offered_rate;
}
