package kr.mydata.apim.vo.mgmts;

import lombok.Data;

/**
 * 기관정보 조회 - 상세
 */
@Data
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
