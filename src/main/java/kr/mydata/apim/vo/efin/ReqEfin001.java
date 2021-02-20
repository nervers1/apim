package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 전자지급수단 목록 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqEfin001 extends APIEntity {
    private String authorization;
    private String org_code;
    private String search_timestamp;
}
