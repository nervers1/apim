package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 청구 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard005 {
  private String rsp_code;
  private String rsp_msg;
  private String next_page;
  private int bill_detail_cnt;
  private List<ResCard005Sub> bill_detail_list;
}
