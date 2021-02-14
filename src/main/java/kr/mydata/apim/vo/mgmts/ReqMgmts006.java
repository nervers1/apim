package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 데이터보유자 상태 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqMgmts006 extends APIEntity {
  private String authorization;
  private String org_code;
}
