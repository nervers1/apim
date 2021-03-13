package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통신 거래내역 목록 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqTelecom003 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "mgmt_id 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 15, message = "mgmt_id 값은 6 자리 입니다.")
    private String mgmt_id;

    @NotNull(message = "from_month 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "from_month 값은 6 자리 입니다.")
    private String from_month;

    @NotNull(message = "to_month 값이 반드시 있어야 합니다.")
    @Size(min = 6, max = 6, message = "to_month 값은 6 자리 입니다.")
    private String to_month;

}
