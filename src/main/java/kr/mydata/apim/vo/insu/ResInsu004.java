package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 자동차보험 정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu004 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int car_insu_cnt;
  private List<ResInsu004Sub> car_insu_list;
}
