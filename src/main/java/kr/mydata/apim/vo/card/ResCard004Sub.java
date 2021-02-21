package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 청구 기본정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard004Sub implements Comparable<ResCard004Sub> {
    private String seqno;
    private String charge_amt;
    private String charge_day;
    private String charge_month;
    private String paid_out_date;
    private String card_type;

    @Override
    public int compareTo(ResCard004Sub o) {
        // 결제년월일 기준 내림차순 정렬
        return o.getPaid_out_date().compareTo(this.paid_out_date);
    }
}
