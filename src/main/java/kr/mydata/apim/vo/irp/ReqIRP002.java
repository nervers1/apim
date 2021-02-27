package kr.mydata.apim.vo.irp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 모험 공통) - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqIRP002 {

    @NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "org_code 값은 1 ~ 10 자리 입니다.")
    private String org_code;

    @NotNull(message = "account_num 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 20, message = "account_num 값은 1 ~ 20 자리 입니다.")
    private String account_num;

    @Size(min = 1, max = 7, message = "seqno 값은 1 ~ 7 자리 입니다.")
    private String seqno;

    @NotNull(message = "search_timestamp 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 14, message = "search_timestamp 값은 14 자리 입니다.")
    private String search_timestamp;
}
