package kr.mydata.apim.vo.card;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 국내 승인내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqCard007 extends APIEntity {
  private String authorization;
  private String org_code;
  private String from_dtime;
  private String to_dtime;
  private ResCard007Sub next_page;
  private int limit;
}
