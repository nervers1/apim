package kr.mydata.apim.vo.ginsu;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보증보험 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResGinsu003Sub implements Comparable<ResGinsu003Sub> {
    private String trans_date;
    private String trans_no;
    private String paid_amt;
    private String pay_method;

    @Override
    public int compareTo(ResGinsu003Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_date.compareTo(this.trans_date);
    }
}
