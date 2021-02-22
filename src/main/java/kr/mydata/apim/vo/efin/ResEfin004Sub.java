package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import kr.mydata.apim.vo.bank.ResBank004Sub;

/**
 * 선불 거래내역 조회 - 거래내역 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin004Sub implements Comparable<ResEfin004Sub> {
    private String trans_type;
    private String fob_name;
    private String trans_dtime;
    private String trans_amt;
    private String balance_amt;
    private String trans_org_code;
    private String trans_id;
    private String trans_memo;
    
    @Override
    public int compareTo(ResEfin004Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
