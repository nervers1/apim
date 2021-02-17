package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 자동차보험 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu004Sub {
  private String car_number;
  @JsonAlias({"car_insu_type", "car_inst_type"})
  private String car_insu_type;
  private String car_name;
  private String start_date;
  private String end_date;
  private String contract_age;
  private String contract_driver;
  private String is_own_dmg_coverage;
  private String self_pay_rate;
  private BigDecimal self_pay_amt;
}
