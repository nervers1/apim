package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 단기대출 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard011Sub {
    private String loan_dtime;
    private BigDecimal loan_amt;
    private String pay_due_date;
    private int int_rate;
}
