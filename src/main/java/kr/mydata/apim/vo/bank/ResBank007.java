package kr.mydata.apim.vo.bank;

import lombok.Data;

import java.util.List;

/**
 * 투자상품계좌 거래내역 조회 - 출력
 */
@Data
public class ResBank007 {
  private String rsp_code;
  private String rsp_msg;
  private ResBank007Sub next_page;
  private int trans_cnt;
  private List<ResBank007Sub> trans_list;
}
