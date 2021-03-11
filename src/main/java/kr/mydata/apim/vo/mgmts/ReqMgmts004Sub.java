package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 통계자료 전송 - 통계 일자별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub {
    @NotNull(message = "statistics_date 값이 반드시 있어야 합니다.")
    @Size(min = 8, max = 8, message = "statistics_date 값은 8 자리 입니다.")
    private String stat_date;

    @NotNull(message = "org_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 3, message = "org_cnt 값은 1 ~ 3 자리 입니다.")
    private String org_cnt;

    @NotNull(message = "org_list 값이 반드시 있어야 합니다.")
    private List<ReqMgmts004Sub2> org_list;
}
