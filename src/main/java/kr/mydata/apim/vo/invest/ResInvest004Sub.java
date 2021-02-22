package kr.mydata.apim.vo.invest;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 계좌 상품정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest004Sub {
    private String prod_type;
    private String prod_type_detail;
    private String prod_code;
    private String prod_name;
    private String purchase_amt;
    private String holding_num;
    private String avail_for_sale_num;
    private String eval_amt;
    private String issue_date;
    private String paid_in_amt;
    private String withdrawal_amt;
    private String last_paid_in_date;
    private String received_amt;
    @JsonAlias({"currency_code", "currenct_code"})
    private String currency_code;
}
