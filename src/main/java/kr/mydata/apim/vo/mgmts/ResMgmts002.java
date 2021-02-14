package kr.mydata.apim.vo.mgmts;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 기관정보 조회 - 출력
 */
@Data
public class ResMgmts002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int org_cnt;
  private List<ResMgmts002Sub> org_list;
}
