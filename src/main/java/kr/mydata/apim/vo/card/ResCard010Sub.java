package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 리볼빙 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard010Sub {
    private String req_date;
    private double min_pay_rate;
    private BigDecimal min_pay_amt;
    private double agreed_pay_rate;
    private BigDecimal remained_amt;
}
