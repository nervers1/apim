package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 서비스정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private int org_cnt;
    private List<ResMgmts003Sub> org_list;
}
