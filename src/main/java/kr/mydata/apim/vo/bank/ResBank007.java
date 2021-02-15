package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 투자상품계좌 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank007 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResBank007Sub> trans_list;
}
