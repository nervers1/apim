package kr.mydata.apim.vo.efin;

import lombok.Data;

import java.util.List;

/**
 * 선불 거래내역 조회 - 출력
 */
@Data
public class ResEpay004 {
  private String rsp_code;
  private String rsp_msg;
  private ResEpay004Sub next_page;
  private int trans_cnt;
  private List<ResEpay004Sub> trans_list;
}
