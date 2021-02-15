package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통계자료 전송 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts004 extends APIEntity {
  private String authorization;
  private String mydata_org_code;
  private String type;
  private String client_id;
  private String inquiry_date;
  private int statistics_date_cnt;
  private List<ReqMgmts004Sub> statistics_date_list;
}
