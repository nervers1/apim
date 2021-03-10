package kr.mydata.apim.vo.capital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResLoan003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String balance_amt;
    private String loan_principal;
    private String next_repay_date;
}
