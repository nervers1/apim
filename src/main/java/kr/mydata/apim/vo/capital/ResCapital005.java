package kr.mydata.apim.vo.capital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 운용리스 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCapital005 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String holder_name;
    private String issue_date;
    private String repay_date;
    private String repay_method;
    private String repay_org_code;
    private String repay_account_num;
    private String next_repay_date;
}
