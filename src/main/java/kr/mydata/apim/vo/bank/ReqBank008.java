package kr.mydata.apim.vo.bank;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 대출상품계좌 기본정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqBank008 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String search_timestamp;
}
