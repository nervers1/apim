package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 자동차보험 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu007Sub implements Comparable<ResInsu007Sub> {
    private String face_amt;
    private String trans_no;
    private String paid_amt;
    private String pay_method;
 
    @Override
    public int compareTo(ResInsu007Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_no.compareTo(this.trans_no);
    }
}
