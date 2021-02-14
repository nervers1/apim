package kr.mydata.apim.vo.card;


import lombok.Data;

import java.util.List;

/**
 * 해외 승인내역 조회 - 출력
 */
@Data
public class ResCard008 {
  private String rsp_code;
  private String rsp_msg;
  private ResCard008Sub next_page;
  private int approved_cnt;
  private List<ResCard008Sub> approved_list;
}
