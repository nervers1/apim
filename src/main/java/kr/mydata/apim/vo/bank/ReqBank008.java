package kr.mydata.apim.vo.bank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 기본정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank008  {

	@NotNull(message = "org_code is null")
	@Size(min = 1, max = 7, message = "org_code size error")
	private String org_code;

	@NotNull(message = "account_num is null")
	@Size(max = 20, message = "account_num size error")
	private String account_num;

	@Size(max = 5, message = "seqno size error")
	private String seqno;

	@NotNull(message = "search_timestamp is null")
	@Size(min = 14, max = 14, message = "search_timestamp size error")
	private String search_timestamp;
}
