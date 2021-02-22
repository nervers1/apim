package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통계자료 전송 - 통계 일자별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub {
	@NotNull(message = "statistics_date 값이 반드시 있어야 합니다.")
    @Size(min = 14, max = 14, message = "statistics_date 값은 14 자리 입니다.")
    private String statistics_date;
	
	@NotNull(message = "org_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 5, message = "org_cnt 값은 1 ~ 5 자리 입니다.")
    private String org_cnt;
    private List<ReqMgmts004Sub2> org_list;
}
