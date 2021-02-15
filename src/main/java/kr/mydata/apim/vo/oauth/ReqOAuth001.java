package kr.mydata.apim.vo.oauth;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqOAuth001 extends APIEntity {
  private String grant_type;
  private String client_id;
  private String client_secret;
  private String scope;
}
