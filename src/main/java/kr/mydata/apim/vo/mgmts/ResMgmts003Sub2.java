package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 서비스정보 조회 - 서비스 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts003Sub2 {
    private String service_name;
    private String op_type;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String client_ip_cnt;
    private List<ResMgmts003Sub3> client_ip_list;
}
