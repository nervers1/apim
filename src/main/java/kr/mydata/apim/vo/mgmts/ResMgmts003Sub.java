package kr.mydata.apim.vo.mgmts;

import lombok.Data;

import java.util.List;

/**
 * 서비스정보 조회 - 상세
 */
@Data
public class ResMgmts003Sub {
  private String org_code;
  private int service_cnt;
  private List<ResMgmts003Sub2> service_list;
}
