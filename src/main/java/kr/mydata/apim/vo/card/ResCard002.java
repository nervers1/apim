package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String card_type;
    private String is_trans_payable;
    private String is_cash_card;
    private String linked_bank_code;
    private String card_brand;
    private BigDecimal annual_fee;
    private String issue_date;
}
