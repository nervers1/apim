package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기관정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts002Sub {
  private String op_type;
  private String org_type;
  private String org_name;
  private String org_regno;
  private String corp_regno;
  private String address;
  private String domain;
  private String relay_org_code;
}
