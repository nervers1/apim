package kr.mydata.apim.vo.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OAuth Error 응답 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResOAuthError {
  private String error;
  private String error_description;
}
