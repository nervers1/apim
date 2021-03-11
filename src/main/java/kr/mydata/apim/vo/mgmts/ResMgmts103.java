package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 정보주체 별 전송요구 내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts103 {
    private String rsp_code;
    private String rsp_msg;
    private String is_member;
    private String service_cnt;
    private List<ResMgmts103Sub> service_list;
}
