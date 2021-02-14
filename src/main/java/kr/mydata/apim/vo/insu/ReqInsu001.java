package kr.mydata.apim.vo.insu;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 보험 목록 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqInsu001 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
