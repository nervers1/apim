package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 기관정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts002 extends APIEntity {
  private String authorization;
  private String search_timestamp;
}
