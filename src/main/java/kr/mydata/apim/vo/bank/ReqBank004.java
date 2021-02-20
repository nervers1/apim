package kr.mydata.apim.vo.bank;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 수신계좌 거래내역 조회 - 입력
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank004 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "org_code 값은 1 ~ 7 자리 입니다.")
    private String org_code;

    @NotNull(message = "account_num 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 20, message = "account_num 값은 1 ~ 20 자리 입니다.")
    private String account_num;

    @Size(min = 1, max = 3, message = "seqno 값은 1 ~ 3 자리 입니다.")
    private String seqno;

    @Size(min = 3, max = 3, message = "currency_code 값은 3 자리 입니다.")
    private String currency_code;

    @NotNull(message = "from_dtime 값이 반드시 있어야 합니다.")
    @Size(min = 14, max = 14, message = "from_dtime 값은 14 자리 입니다.")
    private String from_dtime;

    @NotNull(message = "to_dtime 값이 반드시 있어야 합니다.")
    @Size(min = 14, max = 14, message = "to_dtime 값은 14 자리 입니다.")
    private String to_dtime;

    @Size(min = 1, max = 1000, message = "next_page 값은 1 ~ 1000 자리 입니다.")
    private String next_page;

    @NotNull(message = "limit is null")
    @Size(min = 1, max = 3, message = "limit 값은 1 ~ 3 자리 입니다.")
    private String limit;
}
