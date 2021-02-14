package kr.mydata.apim.vo.common;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 전송요구 내역 조회 (공통) - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqCmn002 extends APIEntity {
  // 접근토큰
  private String authorization;
  // 기관코드
  private String org_code;
}
