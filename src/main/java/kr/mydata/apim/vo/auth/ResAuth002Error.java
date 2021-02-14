package kr.mydata.apim.vo.auth;

import lombok.Data;

/**
 * 접근토큰 발급 요청 - 오류
 */
@Data
public class ResAuth002Error {
  private String error;
  private String error_description;
}
