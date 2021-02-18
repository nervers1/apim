package kr.mydata.apim.vo.card;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 청구 기본정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class ReqCard004 extends APIEntity {
  private String authorization;
  private String org_code;
  private int from_month;
  private int to_month;
  private String next_page;
  private int limit;
  
  
  
  
}