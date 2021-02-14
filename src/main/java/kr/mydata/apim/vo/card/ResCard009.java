package kr.mydata.apim.vo.card;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 대출상품 목록 조회 - 출력
 */
@Data
public class ResCard009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private boolean is_loan_revolving;
  private boolean is_loan_short_term;
  private boolean is_loan_long_term;
}
