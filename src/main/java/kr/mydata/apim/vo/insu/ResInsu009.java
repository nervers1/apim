package kr.mydata.apim.vo.insu;

import lombok.Data;

/**
 * 대출상품 기본정보 조회 - 출력
 */
@Data
public class ResInsu009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String loan_start_date;
  private String loan_exp_date;
}
