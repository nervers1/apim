package kr.mydata.apim.vo.oauth;

import lombok.Data;

/**
 * OAuth Error 응답 - 출력
 */
@Data
public class ResOAuthError {
  private String error;
  private String error_description;
}
