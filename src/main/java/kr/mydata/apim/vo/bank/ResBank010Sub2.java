package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 거래내역 조회 - 이자적용 Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank010Sub2 {
	private String int_start_date; // 이자적용시작일
	private String int_end_date; // 이자적용종료일
	private String int_rate; // 적용이율
	private String int_type; // 이자종류(코드)
}
