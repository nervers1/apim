package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 보험 특약정보 조회 - 상세
 */
@Data
public class ResInsu003Sub {
  private String contract_name;
  private String contract_status;
  private String contract_exp_date;
  private BigDecimal contract_face_amt;
  private String currency_code;
  private String is_required;
}
