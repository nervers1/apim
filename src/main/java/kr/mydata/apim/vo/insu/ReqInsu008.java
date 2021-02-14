package kr.mydata.apim.vo.insu;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 대출상품 목록 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqInsu008 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
