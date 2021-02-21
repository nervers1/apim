package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 청구 추가정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard005Sub implements Comparable<ResCard005Sub> {
    private String card_id;
    private String paid_dtime;
    private String paid_amt;
    private String currency_code;
    private String merchant_name;
    private String credit_fee_amt;
    private String total_install_cnt;
    private String cur_install_cnt;
    private String balance_amt;
    private String prod_type;

    @Override
    public int compareTo(ResCard005Sub o) {
        // 결제년월일 기준 내림차순 정렬
        return o.getPaid_dtime().compareTo(this.paid_dtime);
    }
}
