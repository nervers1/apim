package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통계자료 전송 - API 응답코드 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub5 {

    @NotNull(message = "status_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "status_code 값은 1 ~ 3 자리 입니다.")
    private String status_code;

    @NotNull(message = "status_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 10, message = "status_cnt 값은 1 ~ 10 자리 입니다.")
    private String status_cnt;
}
