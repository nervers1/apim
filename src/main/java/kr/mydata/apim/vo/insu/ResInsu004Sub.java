package kr.mydata.apim.vo.insu;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 자동차보험 정보 조회 - 상세
 */
@Data
public class ResInsu004Sub {
  private String car_number;
  private String car_insu_type;
  private String car_name;
  private String start_date;
  private String end_date;
  private String contract_age;
  private String contract_driver;
  private String is_own_dmg_cover_age;
  private String self_pay_rate;
  private BigDecimal self_pat_amt;
}
