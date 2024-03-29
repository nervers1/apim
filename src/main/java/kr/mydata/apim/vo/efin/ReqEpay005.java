package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 결제내역 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqEpay005 extends APIEntity {
  private String authorization;
  private String org_code;
  private String sub_key;
  private String from_dtime;
  private String to_dtime;
  private String next_page;
  private int limit;
}
