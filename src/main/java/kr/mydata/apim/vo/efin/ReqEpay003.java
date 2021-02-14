package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 전자지급수단 자동충전정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqEpay003 extends APIEntity {
  private String authorization;
  private String org_code;
  private String sub_key;
  private String search_timestamp;
}
