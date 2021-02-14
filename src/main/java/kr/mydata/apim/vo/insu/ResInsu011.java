package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.util.List;

/**
 * 대출상품 거래내역 조회 - 출력
 */
@Data
public class ResInsu011 {
  private String rsp_code;
  private String rsp_msg;
  private ResInsu011Sub next_page;
  private int trans_cnt;
  private List<ResInsu011Sub> trans_list;
}
