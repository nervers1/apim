package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통계자료 전송 - API 구분 별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub3 {
  private String api_type;
  private int tm_slot_cnt;
  private List<ReqMgmts004Sub4> tm_slot_list;
}
