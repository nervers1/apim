package kr.mydata.apim.vo.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 청구 추가정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqCard005 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @Size(min = 1, max = 4, message = "seqno 값은 1 ~ 4 자리 입니다.")
    private String seqno;

    @NotNull(message = "charge_month 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "charge_month 값은 6 자리 입니다.")
    private String charge_month;

    @Size(max = 1000, message = "next_page 값은 ~ 1000 자리 입니다.")
    private String next_page;

    @NotNull(message = "limit 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "limit 값은 1 ~ 3 자리 입니다.")
    private String limit;
}
