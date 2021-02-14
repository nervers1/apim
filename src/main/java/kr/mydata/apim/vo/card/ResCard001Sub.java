package kr.mydata.apim.vo.card;

import lombok.Data;

/**
 * 카드 목록 조회 - 보유카드 정보
 */
@Data
public class ResCard001Sub {
  private String card_id;
  private String card_num;
  private String is_consent;
  private String card_name;
  private String card_member;
}
