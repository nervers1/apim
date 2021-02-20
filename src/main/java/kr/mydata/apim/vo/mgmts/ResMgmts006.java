package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 데이터보유자 상태 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts006 {
    private String rsp_code;
    private String rsp_msg;
    private String availability;
}
