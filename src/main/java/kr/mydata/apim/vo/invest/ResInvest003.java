package kr.mydata.apim.vo.invest;


import lombok.Data;

import java.util.List;

/**
 * 계좌 거래내역 조회 - 출력
 */
@Data
public class ResInvest003 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResInvest003Sub> trans_list;
}
