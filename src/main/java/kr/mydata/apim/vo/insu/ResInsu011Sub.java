package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import kr.mydata.apim.vo.bank.ResBank004Sub;

/**
 * 대출상품 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu011Sub implements Comparable<ResInsu011Sub> {
    private String trans_no;
    private String trans_dtime;
    private String currency_code;
    private String loan_paid_amt;
    private String int_paid_amt;
    private String int_cnt;
    private List<ResInsu011Sub2> int_list;
    
    @Override
    public int compareTo(ResInsu011Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
