package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank001Sub {
  private String account_num;
  private String is_consent;
  private int seqno;
  private String currency_code;
  private String prod_name;
  private String account_type;
  private String account_status;
}
