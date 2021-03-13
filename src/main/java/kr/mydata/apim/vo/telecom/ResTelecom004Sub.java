package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결제 내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom004Sub implements Comparable<ResTelecom004Sub> {
    private String trans_date;
    private String trans_amt;
    private String merchant_name;
    private String trans_title;

    @Override
    public int compareTo(ResTelecom004Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_date.compareTo(this.trans_date);
    }
}
