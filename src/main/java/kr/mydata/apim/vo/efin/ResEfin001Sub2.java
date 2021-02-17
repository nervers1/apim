package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전자지급수단 목록 조회 - 계정항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin001Sub2 {
  private String pay_org_code;
  private String pay_id;
  private String is_primary;
}
