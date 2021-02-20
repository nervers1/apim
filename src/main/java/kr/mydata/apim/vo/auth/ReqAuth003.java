package kr.mydata.apim.vo.auth;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 접근토큰 발급 갱신 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqAuth003 extends APIEntity {
    private String org_code;
    private String grant_type;
    private String refresh_token;
    private String client_id;
    private String client_secret;
}
