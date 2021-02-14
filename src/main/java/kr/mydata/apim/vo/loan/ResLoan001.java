package kr.mydata.apim.vo.loan;

import lombok.Data;

import java.util.List;

/**
 * 계좌 목록 조회 - 출력
 */
@Data
public class ResLoan001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String reg_date;
  private int account_cnt;
  private List<ResLoan001Sub> account_list;
}