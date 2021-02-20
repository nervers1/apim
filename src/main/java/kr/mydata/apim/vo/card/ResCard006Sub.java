package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 결제정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard006Sub {
    private String seqno;
    private String pay_due_date;
    private BigDecimal pay_amt;
}
