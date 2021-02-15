package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 수신계좌 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank003 {
	private String rsp_code;
	private String rsp_msg;
	private String search_timestamp;
	private String current_balance_amt;
	private String withdrawable_amt;
	private String offered_rate;
	private String last_paid_in_cnt;
}
