package kr.mydata.apim.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 요청 - 오류
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResAuth002Error {
  private String error;
  private String error_description;
}
