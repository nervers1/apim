package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 서비스정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts005 {

    @NotNull(message = "search_timestamp 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 14, message = "search_timestamp 값은 1 ~ 14 자리 입니다.")
    private String search_timestamp;
}
