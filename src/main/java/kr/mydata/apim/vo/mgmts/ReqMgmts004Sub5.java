package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 통계자료 전송 - API 응답코드 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub5 {
  private String status_code;
  private int status_cnt;
}
