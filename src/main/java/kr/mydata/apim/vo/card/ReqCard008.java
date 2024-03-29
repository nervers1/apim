package kr.mydata.apim.vo.card;


import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 해외 승인내역 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqCard008 extends APIEntity {
  private String authorization;
  private String org_code;
  private String from_dtime;
  private String to_dtime;
  private String next_page;
  private int limit;
}
