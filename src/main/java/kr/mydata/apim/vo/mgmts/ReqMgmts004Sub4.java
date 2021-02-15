package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 통계자료 전송 - 시간대별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub4 {
  private String tm_slot;
  private int rsp_med;
  private int rsp_avg;
  private BigDecimal rsp_total;
  private int rsp_stdev;
  private int api_status_cnt;
  private List<ReqMgmts004Sub5> api_status_list;
}
