package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 대출상품 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu008 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int loan_cnt;
  private List<ResInsu008Sub> loan_list;
}
