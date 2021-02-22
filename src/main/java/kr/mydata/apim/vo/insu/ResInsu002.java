package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 보험 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String is_renewable;
    private String issue_date;
    private String exp_date;
    private BigDecimal face_amt;
    private String currency_code;
    private String is_variable;
    private String is_universal;
    private String pension_rcv_start_date;
    private String pension_rcv_cycle;
    private String is_loanable;
    private String insured_count;
    private List<ResInsu002Sub> insured_list;
}
