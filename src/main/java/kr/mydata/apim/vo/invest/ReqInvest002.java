package kr.mydata.apim.vo.invest;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 계좌 기본정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqInvest002 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String search_timestamp;
}
