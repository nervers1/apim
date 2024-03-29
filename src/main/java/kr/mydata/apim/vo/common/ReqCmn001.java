package kr.mydata.apim.vo.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * API 목록 조회 (공통) - 입력 api: /apis Header: - Authorization 접근토큰 Parameter: -
 * org_code 기관코드 aN(7) - 지원 API로부터 배포
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqCmn001 {

	@NotNull(message = "org_code is null")
	@Size(min = 1, max = 7, message = "org_code size error")
	private String org_code;

}
