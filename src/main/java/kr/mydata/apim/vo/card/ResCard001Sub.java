package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 카드 목록 조회 - 보유카드 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard001Sub {
	private String card_id;
	private String card_num;
	private String is_consent;
	private String card_name;
	private String card_member;
}
