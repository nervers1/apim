package kr.mydata.apim.vo.bank;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 투자상품계좌 거래내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqBank007 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String from_dtime;
  private String to_dtime;
  private String next_page;
  private int limit;
}
