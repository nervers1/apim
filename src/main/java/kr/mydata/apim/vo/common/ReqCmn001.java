package kr.mydata.apim.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * API 목록 조회 (공통) - 입력
 * api: /apis
 * Header:
 * - Authorization 접근토큰
 * Parameter:
 * - org_code   기관코드   aN(7)   - 지원 API로부터 배포
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqCmn001 {
    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;
}
