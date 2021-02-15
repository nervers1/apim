package kr.mydata.apim.vo.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 전송요구 내역 조회 (공통) - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqCmn002 {

	@NotNull(message = "org_code is null")
	@Size(min = 1, max = 7, message = "org_code size error")
	private String org_code;
}
