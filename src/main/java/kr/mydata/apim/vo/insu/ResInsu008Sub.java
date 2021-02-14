package kr.mydata.apim.vo.insu;

import lombok.Data;

/**
 * 대출상품 목록 조회 - 상세
 */
@Data
public class ResInsu008Sub {
  private String prod_name;
  private String account_num;
  private String is_consent;
  private String account_type;
  private String account_status;
}
