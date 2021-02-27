package kr.mydata.apim.vo.irp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 모험 공통) - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String accum_amt;
    private String eval_amt;
    private String employer_amt;
    private String employee_amt;
    private String issue_date;
    private String first_deposit_date;
}
