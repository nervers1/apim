package kr.mydata.apim.vo.oauth;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 접근토큰 발급 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqOAuth001 extends APIEntity {
    @NotNull(message = "grant_type 값이 반드시 있어야 합니다.")
    @Size(min= 18, max = 18, message = "grant_type 값은 18 자리이며 'client_credentials' 고정 입니다.")
    private String grant_type;

    @NotNull(message = "client_id 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_id 값은 1 ~ 50 자리 입니다.")
    private String client_id;

    @NotNull(message = "client_secret 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_secret 값은 1 ~ 50 자리 입니다.")
    private String client_secret;

    @NotNull(message = "scope 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "scope 값은 6 자리이며 'manage' 고정 입니다.")
    private String scope;
}
