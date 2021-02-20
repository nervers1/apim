package kr.mydata.apim.vo.bank;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 투자상품계좌 추가정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank006 extends APIEntity {
    private String authorization;
    private String org_code;
    private String account_num;
    private String search_timestamp;
}
