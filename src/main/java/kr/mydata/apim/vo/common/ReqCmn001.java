package kr.mydata.apim.vo.common;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
API 목록 조회 (공통) - 입력
api: /apis
Header:
    - Authorization 접근토큰
Parameter:
    - org_code   기관코드   aN(7)   - 지원 API로부터 배포
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ReqCmn001 extends APIEntity {
  // 접근토큰
  private String authorization;
  // 기관코드
  private String org_code;

}
