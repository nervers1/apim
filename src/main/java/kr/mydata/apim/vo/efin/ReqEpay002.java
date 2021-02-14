package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 전자지급수단 잔액정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqEpay002 extends APIEntity {
  private String authorization;
  private String org_code;
  private String sub_key;
  private String search_timestamp;
}
