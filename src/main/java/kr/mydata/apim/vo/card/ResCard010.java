package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 리볼빙 정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard010 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int revolving_month;
  private int revolving_cnt;
  private List<ResCard010Sub> revolving_list;
}
