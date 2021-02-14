package kr.mydata.apim.vo.loan;

import lombok.Data;

import java.util.List;

/**
 * 대출상품계좌 거래내역 조회 - 출력
 */
@Data
public class ResLoan004 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResLoan004Sub> trans_list;
}
