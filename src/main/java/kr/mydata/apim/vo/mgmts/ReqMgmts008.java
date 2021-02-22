package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 통계자료 재전송 요청 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts008{
    private String authorization;
    private String mydata_org_code;
    private String inquiry_date;
}
