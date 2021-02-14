package kr.mydata.apim.vo.auth;

import lombok.Data;

/**
 * 접근토큰 발급 요청 - 출력
 */
@Data
public class ResAuth002 {
  private String tokent_type;
  private String acces_token;
  private int expire_in;
  private String refresh_token;
  private int refresh_token_expires_in;
  private String scope;
}
