package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장기대출 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard012Sub {
    private String loan_dtime;
    private String loan_cnt;
    private String loan_type;
    private String loan_name;
    private String loan_amt;
    private String int_rate;
    private String exp_date;
    private String balance_amt;
    private String repay_method;
    private String int_amt;
}
