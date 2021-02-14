package kr.mydata.apim.vo.card;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 단기대출 정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqCard011 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
