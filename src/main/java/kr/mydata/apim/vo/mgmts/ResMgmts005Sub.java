package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통합인증 API 호출용 자격증명 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts005Sub {
    private String org_code;
    private String op_type;
    private String client_id;
    private String client_secret;
    private String client_ip_cnt;
    private List<ResMgmts005Sub2> client_ip_list;
}
