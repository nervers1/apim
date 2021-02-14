package kr.mydata.apim.vo.auth;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 접근토큰 폐기 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqAuth004 extends APIEntity {
  private String org_code;
  private String token;
  private String token_type_hint;
  private String client_id;
  private String client_secret;
}
