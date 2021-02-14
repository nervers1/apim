package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 정보주체 별 전송요구 내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts007 extends APIEntity {
  private String authorization;
  private String org_code;
  private String user_ci;
}
