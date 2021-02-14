package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.util.List;

/**
 * 대출상품 목록 조회 - 출력
 */
@Data
public class ResInsu008 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int loan_cnt;
  private List<ResInsu008Sub> loan_list;
}
