package kr.mydata.apim.vo.invest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest003Sub implements Comparable<ResInvest003Sub> {
    private String prod_name;
    private String prod_code;
    private String trans_dtime;
    private String trans_type;
    private String trans_type_detail;
    private String trans_num;
    private String base_amt;
    private String trans_amt;
    private String settle_amt;
    private String balance_amt;
    private String currency_code;

    @Override
    public int compareTo(ResInvest003Sub o) {
        // 거래일시 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
