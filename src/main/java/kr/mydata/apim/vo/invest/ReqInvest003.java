package kr.mydata.apim.vo.invest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 계좌 거래내역 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqInvest003 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "account_num 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 20, message = "account_num 값은 1 ~ 20 자리 입니다.")
    private String account_num;

    @NotNull(message = "from_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "from_date 값은 14 자리 입니다.")
    private String from_date;

    @NotNull(message = "to_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "to_date 값은 8 자리 입니다.")
    private String to_date;

    @Size(max = 1000, message = "next_page 값은 ~ 1000 자리 입니다.")
    private String next_page;

    @NotNull(message = "limit 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "limit 값은 1 ~ 3 자리 입니다.")
    private String limit;
}
