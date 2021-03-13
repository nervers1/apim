package kr.mydata.apim.vo.ginsu;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 보증보험 거래내역 조회 - 입력
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqGinsu003 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "insu_num 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 20, message = "insu_num 값은 1 ~ 20 자리 입니다.")
    private String insu_num;

    @NotNull(message = "from_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "from_date 값은 8 자리 입니다.")
    private String from_date;

    @NotNull(message = "to_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "to_date 값은 8 자리 입니다.")
    private String to_date;

    @Size(min = 1, max = 1000, message = "next_page 값은 1 ~ 1000 자리 입니다.")
    private String next_page;

    @NotNull(message = "limit 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "limit 값은 1 ~ 3 자리 입니다.")
    private String limit;
}
