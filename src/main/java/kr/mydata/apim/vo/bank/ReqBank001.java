package kr.mydata.apim.vo.bank;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 계좌 목록 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank001 {
    @NotNull(message = "org_code is null")
    @Size(min = 1, max = 7, message = "org_code size error")
    private String org_code;

    @NotNull(message = "search_timestamp is null")
    @Size(min = 14, max = 14, message = "search_timestamp size error")
    private String search_timestamp;
}
