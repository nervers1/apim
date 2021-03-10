package kr.mydata.apim.vo.capital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 대출상품계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResLoan004Sub implements Comparable<ResLoan004Sub> {
    private String trans_dtime;
    private String trans_no;
    private String trans_type;
    private String trans_amt;
    private String balance_amt;
    private String principal_amt;
    private String int_amt;
    private String int_cnt;
    private List<ResLoan004Sub2> int_list;

    @Override
    public int compareTo(ResLoan004Sub o) {
        return o.getTrans_dtime().compareTo(this.trans_dtime);
    }
}
