package kr.mydata.apim.vo.card;


import lombok.Data;

import java.util.List;

/**
 * 단기대출 정보 조회 - 출력
 */
@Data
public class ResCard011 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int short_term_cnt;
  private List<ResCard011Sub> short_term_list;
}
