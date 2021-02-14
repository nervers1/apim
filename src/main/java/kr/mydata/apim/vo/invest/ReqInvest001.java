package kr.mydata.apim.vo.invest;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 계좌 목록 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqInvest001 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
