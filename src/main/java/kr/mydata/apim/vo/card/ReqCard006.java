package kr.mydata.apim.vo.card;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 결제정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqCard006 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
