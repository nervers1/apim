package kr.mydata.apim.vo.card;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 장기대출 정보 조회 - 출력
 */
@Data
public class ResCard012 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int long_term_cnt;
  private List<ResCard012Sub> long_term_list;
}
