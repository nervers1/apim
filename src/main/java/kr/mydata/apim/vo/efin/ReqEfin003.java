package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 전자지급수단 자동충전정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqEfin003 extends APIEntity {
    private String authorization;
    private String org_code;
    private String sub_key;
    private String search_timestamp;
}
