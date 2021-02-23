package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통계자료 전송 - 데이터보유자별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub2 {
	@NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "org_code 값은 1 ~ 7 자리 입니다.")
    private String org_code;

	@NotNull(message = "token_new 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "token_new 값은 1 ~ 10 자리 입니다.")
    private String token_new;

	@NotNull(message = "token_renew 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "token_renew 값은 1 ~ 10 자리 입니다.")
    private String token_renew;

	@NotNull(message = "token_revoke 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "token_revoke 값은 1 ~ 10 자리 입니다.")
    private String token_revoke;

	@NotNull(message = "token_own 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "token_own 값은 1 ~ 10 자리 입니다.")
    private String token_own;

	@NotNull(message = "api_type_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "api_type_cnt 값은 1 ~ 3 자리 입니다.")
    private String api_type_cnt;

	@NotNull(message = "api_type_list 값이 반드시 있어야 합니다.")
    private List<ReqMgmts004Sub3> api_type_list;
}
