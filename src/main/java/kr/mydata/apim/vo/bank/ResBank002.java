package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 수신계좌 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank002 {
	private String rsp_code;
	private String rsp_msg;
	private String search_timestamp;
	private String saving_method;
	private String holder_name;
	private String issue_date;
	private String exp_date;
	private String currency_code;
	private String commit_amt;
	private String monthly_paid_in_amt;
	private String termination_amt;
	private String last_offered_rate;
}
