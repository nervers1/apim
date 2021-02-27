package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 청구 기본정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class ReqCard004 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "from_month 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "from_month 값은 6 자리 입니다.")
    private String from_month;

    @NotNull(message = "to_month 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "to_month 값은 6 자리 입니다.")
    private String to_month;

    @Size(min = 1, max = 1000, message = "next_page 값은 1 ~ 1000 자리 입니다.")
    private String next_page;

    @NotNull(message = "limit 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "limit 값은 1 ~ 3 자리 입니다.")
    private String limit;
}
