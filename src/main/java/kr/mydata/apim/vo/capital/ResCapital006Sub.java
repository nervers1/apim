package kr.mydata.apim.vo.capital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCapital006Sub implements Comparable<ResCapital006Sub> {
    private String trans_dtime;
    private String trans_no;
    private String trans_type;
    private String trans_amt;

    @Override
    public int compareTo(ResCapital006Sub o) {
        return o.getTrans_dtime().compareTo(this.trans_dtime);
    }
}
