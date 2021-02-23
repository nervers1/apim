package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통계자료 재전송 요청 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts008 {

    @NotNull(message = "mydata_org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "mydata_org_code 값은 1 ~ 7 자리 입니다.")
    private String mydata_org_code;

    @NotNull(message = "inquiry_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "inquiry_date 값은 8 자리 입니다.")
    private String inquiry_date;
}
