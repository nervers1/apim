package kr.mydata.apim.vo.oauth;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 접근토큰 발급 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqOAuth001  extends APIEntity {
  private String grant_type;
  private String client_id;
  private String client_secret;
  private String scope;
}
