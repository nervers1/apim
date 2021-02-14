package kr.mydata.apim.vo.oauth;

import lombok.Data;

/**
 * 접근토큰 발급 - 출력
 */
@Data
public class ResOAuth001 {
  private String token_type;
  private String access_token;
  private int expires_in;
  private String scope;
}
