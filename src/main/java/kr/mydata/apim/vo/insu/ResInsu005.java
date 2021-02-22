package kr.mydata.apim.vo.insu;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보험 납입정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu005 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String pay_due;
    private String pay_cycle;
    private String pay_cnt;
    private String pay_org_code;
    private String pay_date;
    private String pay_end_date;
    private String pay_amt;
    private String currency_code;
    private String is_auto_pay;
}
