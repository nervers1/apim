package kr.mydata.apim.vo.irp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통) - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP004 {
	private String rsp_code;
	private String rsp_msg;
	private String next_page;
	private String trans_cnt;
	private List<ResIRP004Sub> trans_list;
}
