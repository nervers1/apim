package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 통계자료 전송 - 시간대별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub4 {

    @NotNull(message = "tm_slot 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 2, message = "tm_slot 값은 1 ~ 2 자리 입니다.")
    private String tm_slot;

    @NotNull(message = "rsp_med 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 6, message = "rsp_med 값은 1 ~ 6 자리 입니다.")
    private String rsp_med;

    @NotNull(message = "rsp_avg 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 6, message = "rsp_avg 값은 1 ~ 6 자리 입니다.")
    private String rsp_avg;

    @NotNull(message = "rsp_total 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 15, message = "rsp_total 값은 1 ~ 15 자리 입니다.")
    private String rsp_total;

    @NotNull(message = "rsp_stdev 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 6, message = "rsp_stdev 값은 1 ~ 6 자리 입니다.")
    private String rsp_stdev;

    @NotNull(message = "api_status_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 1, message = "api_status_cnt 값은 1 자리 입니다.")
    private String status_cnt;

    @NotNull(message = "api_status_list 값이 반드시 있어야 합니다.")
    private List<ReqMgmts004Sub5> status_list;
}
