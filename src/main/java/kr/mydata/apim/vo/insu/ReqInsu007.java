package kr.mydata.apim.vo.insu;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 자동차보험 거래내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqInsu007 extends APIEntity {
  private String authorization;
  private String org_code;
  private String insu_num;
  private String from_date;
  private String to_date;
  private String next_page;
  private int limit;
}
