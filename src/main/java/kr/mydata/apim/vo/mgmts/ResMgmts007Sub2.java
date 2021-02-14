package kr.mydata.apim.vo.mgmts;

import lombok.Data;

/**
 * 정보주체 별 전송요구 내역 조회 - 전송요구 항목
 */
@Data
public class ResMgmts007Sub2 {
  private String consent_type;
  private String consent_date;
  private String consent_exp_date;
}
