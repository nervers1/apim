package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리볼빙 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard010Sub implements Comparable<ResCard010Sub> {
    private String req_date;
    private String min_pay_rate;
    private String min_pay_amt;
    private String agreed_pay_rate;
    private String remained_amt;

    @Override
    public int compareTo(ResCard010Sub o) {
        return o.getReq_date().compareTo(this.req_date);
    }
}
