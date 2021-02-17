package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 해외 승인내역 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard008Sub {
  private String approved_num;
  private String status;
  private String pay_type;
  private String approved_dtime;
  private String cancel_dtime;
  private String merchant_name;
  private BigDecimal approved_amt;
  @JsonAlias({"country_code", "contry_code"})
  private String country_code; // 오타수정
  private String currency_code;
  private BigDecimal krw_amt;
}
