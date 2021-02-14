package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.util.List;

/**
 * 자동차보험 거래내역 조회 - 출력
 */
@Data
public class ResInsu007 {
  private String rsp_code;
  private String rsp_msg;
  private ResInsu007Sub next_page;
  private int trans_cnt;
  private List<ResInsu007Sub> trans_list;
}
