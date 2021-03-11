package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통합인증 API 호출용 자격증명 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts005 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String org_cnt;
    private List<ResMgmts005Sub> org_list;
}
