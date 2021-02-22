package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 해외 승인내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard008Sub implements Comparable<ResCard008Sub> {
    private String approved_num;
    private String status;
    private String pay_type;
    private String approved_dtime;
    private String cancel_dtime;
    private String merchant_name;
    private String approved_amt;
    private String country_code;
    private String currency_code;
    private String krw_amt;

    @Override
    public int compareTo(ResCard008Sub o) {
        return o.getApproved_dtime().compareTo(approved_dtime);
    }
}
