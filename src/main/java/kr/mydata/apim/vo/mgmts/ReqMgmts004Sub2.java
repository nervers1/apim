package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통계자료 전송 - 데이터보유자별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub2 {
    private String org_code;
    private String token_new;
    private String token_renew;
    private String token_revoke;
    private String token_own;
    private String api_type_cnt;
    private List<ReqMgmts004Sub3> api_type_list;
}
