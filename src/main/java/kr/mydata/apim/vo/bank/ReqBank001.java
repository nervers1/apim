package kr.mydata.apim.vo.bank;

import lombok.Data;

/**
 * 계좌 목록 조회 - 입력
 */
@Data
public class ReqBank001 {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
