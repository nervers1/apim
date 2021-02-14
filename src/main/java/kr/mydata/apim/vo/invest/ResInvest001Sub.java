package kr.mydata.apim.vo.invest;


import lombok.Data;

/**
 * 계좌 목록 조회 - 상세
 */
@Data
public class ResInvest001Sub {
  private String account_num;
  private String is_consent;
  private String account_name;
  private String account_type;
  private String account_status;
}
