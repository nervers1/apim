package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 투자상품계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank007Sub {
	private String trans_dtime;
	private String trans_no;
	private String trans_type;
	private String currency_code;
	private String base_amt;
	private String trans_fund_num;
	private String trans_amt;
	private String balance_amt;
}
