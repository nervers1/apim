package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 서비스정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts003Sub {
  private String org_code;
  private int service_cnt;
  private List<ResMgmts003Sub2> service_list;
}
