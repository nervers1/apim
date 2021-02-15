package kr.mydata.apim.vo.invest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 계좌 상품정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest004 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int prod_cnt;
  private List<ResInvest004Sub> prod_list;
}
