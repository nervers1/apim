package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 단기대출 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard011Sub implements Comparable<ResCard011Sub> {
    private String loan_dtime;
    private String loan_amt;
    private String pay_due_date;
    private String int_rate;

    @Override
    public int compareTo(ResCard011Sub o) {
        return o.getLoan_dtime().compareTo(this.loan_dtime);
    }
}
