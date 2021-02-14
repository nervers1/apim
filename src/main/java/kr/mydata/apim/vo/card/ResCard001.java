package kr.mydata.apim.vo.card;

import lombok.Data;

import java.util.List;

/**
 * 카드 목록 조회 - 출력
 */
@Data
public class ResCard001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int card_cnt;
  private List<ResCard001Sub> card_list;
}
