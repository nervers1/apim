package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 정보주체 별 전송요구 내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts007Sub {
    private String service_name;
    private String client_id;
    private int consent_cnt;
    private List<ResMgmts007Sub2> consent_list;
}
