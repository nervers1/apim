package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard009 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String is_loan_revolving;
  private String is_loan_short_term;
  private String is_loan_long_term;
}
