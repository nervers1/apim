package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import kr.mydata.apim.vo.bank.ResBank004Sub;

/**
 * 결제내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin005Sub implements Comparable<ResEfin005Sub>  {
    private String trans_type;
    private String fob_name;
    private String trans_num;
    private String trans_dtime;
    private String trans_amt;
    private String trans_org_code;
    private String trans_id;
    private String total_install_cnt;
    private String merchant_name;
    private String trans_title;
    private String pay_method;
    

    @Override
    public int compareTo(ResEfin005Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
