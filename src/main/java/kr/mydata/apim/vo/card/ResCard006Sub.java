package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결제정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard006Sub implements Comparable<ResCard006Sub> {
    private String seqno;
    private String pay_due_date;
    private String pay_amt;

    @Override
    public int compareTo(ResCard006Sub o) {
        // 결제예정일 기준 내림차순 정렬
        return o.getPay_due_date().compareTo(this.pay_due_date);
    }
}
