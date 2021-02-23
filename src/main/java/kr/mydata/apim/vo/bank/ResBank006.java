package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 투자상품계좌 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank006 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String currency_code;
    private String balance_amt;
    private String eval_amt;
    private String inv_principal;
    private String fund_num;
}
