package kr.mydata.apim.vo.insu;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보험 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu006Sub implements Comparable<ResInsu006Sub> {
    private String trans_date;
    private String trans_applied_month;
    private String trans_no;
    private String paid_amt;
    private String currency_code;
    private String pay_method;

    @Override
    public int compareTo(ResInsu006Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_date.compareTo(this.trans_date);
    }
}
