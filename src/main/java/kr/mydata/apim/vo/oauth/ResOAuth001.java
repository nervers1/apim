package kr.mydata.apim.vo.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResOAuth001 {
    private String token_type;
    private String access_token;
    private int expires_in;
    private String scope;
}
