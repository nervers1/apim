package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 결제내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEpay005 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int trans_cnt;
  private List<ResEpay005Sub> trans_list;
}
