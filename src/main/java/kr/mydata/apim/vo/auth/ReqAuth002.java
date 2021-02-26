package kr.mydata.apim.vo.auth;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 접근토큰 발급 요청 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqAuth002 extends APIEntity {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "grant_type 값은 authorization_code 고정입니다.")
    private String grant_type;

    @NotNull(message = "code 값이 반드시 있어야 합니다.")
    private String code;

    @NotNull(message = "client_id 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_id 값은 1 ~ 50 자리 입니다.")
    private String client_id;

    @NotNull(message = "client_secret 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_secret 값은 1 ~ 50 자리 입니다.")
    private String client_secret;

    @NotNull(message = "redirect_uri 값이 반드시 있어야 합니다.")
    private String redirect_uri;
}
