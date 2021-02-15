package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 투자상품계좌 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank005 {
	private String rsp_code;
	private String rsp_msg;
	private String search_timestamp;
	private String standard_fund_code;
	private String paid_in_type;
	private String issue_date;
	private String exp_date;
}
