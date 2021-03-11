package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 통합인증 API 호출용 자격증명 조회 - 클라이언트 IP 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts005Sub2 {
    private String client_ip;
}
