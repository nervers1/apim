package kr.mydata.apim.vo.auth;

import lombok.Data;


/**
 * 인가코드 발급 요청 - 출력
 */
@Data
public class ResAuth001 {
  private String code;
  private String state;
}
