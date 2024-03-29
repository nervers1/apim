package kr.mydata.apim.vo.irp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통) - 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP003Sub {
	private String irp_name;
	private String irp_type;
	private String eval_amt;
	private String inv_principal;
	private String fund_num;
	private String open_date;
	private String exp_date;
	private String int_rate;
}
