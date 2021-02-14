package kr.mydata.apim.vo.mgmts;

import lombok.Data;

import java.util.List;

/**
 * 정보주체 별 전송요구 내역 조회 - 출력
 */
@Data
public class ResMgmts007 {
  private String rsp_code;
  private String rsp_msg;
  private String is_member;
  private int service_cnt;
  private List<ResMgmts007Sub> service_list;
}
