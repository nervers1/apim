package kr.mydata.apim.vo.insu;


import lombok.Data;

import java.util.List;

/**
 * 보험 거래내역 조회 - 출력
 */
@Data
public class ResInsu006 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResInsu004Sub> trans_list;
}
