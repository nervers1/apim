package kr.mydata.apim.vo.bank;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 거래내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank010Sub {
	private String trans_dtime; // 거래일시
	private String trans_no; // 거래번호
	private String trans_type; // 거래유형
	private String trans_amt; // 거래금액
	private String balance_amt; // 거래 후 대출잔액
	private String trans_principal_amt; // 거래금액 중 원금
	private String trans_int_amt; // 거래금액 중 이자
	private String int_cnt; // 이자적용수
	private List<ResBank010Sub2> int_list; // 이자적용목록
}
