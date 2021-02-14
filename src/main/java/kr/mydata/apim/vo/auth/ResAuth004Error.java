package kr.mydata.apim.vo.auth;

import lombok.Data;

/**
 * 접근토큰 폐기 - 오류
 */
@Data
public class ResAuth004Error {
  private String error;
  private String error_description;
}
