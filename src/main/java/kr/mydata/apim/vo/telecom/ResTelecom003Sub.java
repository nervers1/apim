package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 통신 거래내역 목록 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom003Sub implements Comparable<ResTelecom003Sub> {
    private String trans_month;
    private String paid_amt;
    private String pay_method;

    @Override
    public int compareTo(ResTelecom003Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_month.compareTo(this.trans_month);
    }
}
