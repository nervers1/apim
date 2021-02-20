package kr.mydata.apim.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 갱신 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResAuth003 {
    private String tokent_type;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;
    private String scope;
}
