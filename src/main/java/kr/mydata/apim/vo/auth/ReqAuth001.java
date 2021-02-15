package kr.mydata.apim.vo.auth;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 인가코드 발급 요청 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqAuth001 extends APIEntity {
  private String org_code;
  private String response_type;
  private String client_id;
  private String redirect_uri;
  private String state;
}

