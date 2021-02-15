package kr.mydata.apim.vo.insu;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 자동차보험 정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqInsu004 extends APIEntity {
  private String authorization;
  private String org_code;
  private String insu_num;
  private String search_timestamp;
}
