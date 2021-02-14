package kr.mydata.apim.vo.card;

import lombok.Data;

/**
 * 대출상품 목록 조회 - 출력
 */
@Data
public class ResCard009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String is_loan_revolving;
  private String is_loan_short_term;
  private String is_loan_long_term;
}
