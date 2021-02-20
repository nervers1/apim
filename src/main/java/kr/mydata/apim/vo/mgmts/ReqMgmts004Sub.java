package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통계자료 전송 - 통계 일자별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub {
    private String statistics_date;
    private int org_cnt;
    private List<ReqMgmts004Sub2> org_list;
}
