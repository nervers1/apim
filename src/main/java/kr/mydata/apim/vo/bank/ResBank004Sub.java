package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 수신계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank004Sub implements Comparable<ResBank004Sub> {
    private String trans_dtime;
    private String trans_no;
    private String trans_type;
    private String trans_class;
    private String currency_code;
    private String trans_amt;
    private String balance_amt;
    private String paid_in_cnt;

    @Override
    public int compareTo(ResBank004Sub o) {
        // 거래일시 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
