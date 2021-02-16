package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 국내 승인내역 조회 - 상세
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCard007Sub {
	private String approved_num;
	private String status;
	private String pay_type;
	private String approved_dtime;
	private String cancel_dtime;
	private String merchant_name;
	private String approved_amt;
	private String total_install_cnt;
}
