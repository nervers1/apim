package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 통계자료 재전송 요청 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts008 extends APIEntity {
  private String authorization;
  private String mydata_org_code;
  private String inquiry_date;
}
