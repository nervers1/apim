package kr.mydata.apim.vo.card;


import lombok.Data;

import java.util.List;

/**
 * 결제정보 조회 - 출력
 */
@Data
public class ResCard006 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int pay_cnt;
  private List<ResCard006Sub> pay_list;
}
