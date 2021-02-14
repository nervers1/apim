package kr.mydata.apim.vo.efin;

import lombok.Data;

import java.util.List;

/**
 * 결제내역 조회 - 출력
 */
@Data
public class ResEpay005 {
  private String rsp_code;
  private String rsp_msg;
  private ResEpay005Sub next_page;
  private int trans_cnt;
  private List<ResEpay005Sub> trans_list;
}
