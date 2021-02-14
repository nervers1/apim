package kr.mydata.apim.vo.bank;

import lombok.Data;

import java.util.List;

/**
 * 계좌 목록 조회 - 출력
 */
@Data
public class ResBank001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String reg_date;
  private int account_cnt;
  private List<ResBank001Sub> account_list;
}
