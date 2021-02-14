package kr.mydata.apim.vo.card;


import lombok.Data;

import java.util.List;

/**
 * 국내 승인내역 조회 - 출력
 */
@Data
public class ResCard007 {
  private String rsp_code;
  private String rsp_msg;
  private ResCard007Sub next_page;
  private int approved_cnt;
  private List<ResCard007Sub> approved_list;
}
