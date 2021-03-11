package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts101 {
    private String token_type;
    private String access_token;
    private String expires_in;
    private String scope;
}
