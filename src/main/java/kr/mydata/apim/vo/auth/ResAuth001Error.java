package kr.mydata.apim.vo.auth;

import lombok.Data;

/**
 * 인가코드 발급 요청 - 오류
 */
@Data
public class ResAuth001Error {
  private String error;
  private String error_description;
}
