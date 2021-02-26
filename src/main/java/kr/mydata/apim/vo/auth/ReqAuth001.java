package kr.mydata.apim.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 인가코드 발급 요청 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqAuth001 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "response_type 값은 code 고정입니다.")
    private String response_type;

    @NotNull(message = "client_id 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_id 값은 1 ~ 50 자리 입니다.")
    private String client_id;

    @NotNull(message = "redirect_uri 값이 반드시 있어야 합니다.")
    private String redirect_uri;

    @NotNull(message = "state 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 40, message = "state 값은 1 ~ 40 자리 입니다.")
    private String state;
}

