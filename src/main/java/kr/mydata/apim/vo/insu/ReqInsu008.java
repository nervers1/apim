package kr.mydata.apim.vo.insu;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 대출상품 목록 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqInsu008{
	@NotNull(message = "authorization 값이 반드시 있어야 합니다.")
    private String authorization;
	
	@NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "org_code 값은 1 ~ 7 자리 입니다.")
    private String org_code;
	
	@NotNull(message = "search_timestamp 값이 반드시 있어야 합니다.")
    @Size(min = 14, max = 14, message = "search_timestamp 값은 14 자리 입니다.")
    private String search_timestamp;
}
