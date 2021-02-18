package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 대출상품 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResInsu011 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResInsu011Sub> trans_list;
}
