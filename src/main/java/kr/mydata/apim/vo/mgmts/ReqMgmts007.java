package kr.mydata.apim.vo.mgmts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 정보주체 별 전송요구 내역 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts007 {

	@NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "org_code 값은 1 ~ 7 자리 입니다.")
    private String org_code;

	@NotNull(message = "user_ci 값이 반드시 있어야 합니다.")
    private String user_ci;
}
