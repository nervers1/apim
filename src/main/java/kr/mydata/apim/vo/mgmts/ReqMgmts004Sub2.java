package kr.mydata.apim.vo.mgmts;

import lombok.Data;

import java.util.List;

/**
 * 통계자료 전송 - 데이터보유자별 통계정보 항목
 */
@Data
public class ReqMgmts004Sub2 {
  private String org_code;
  private int token_new;
  private int token_renew;
  private int token_revoke;
  private int token_own;
  private int api_type_cnt;
  private List<ReqMgmts004Sub3> api_type_list;
}
