package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보험 목록 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu001Sub {
  private String insu_num;
  private String is_consent;
  private String prod_name;
  private String insu_type;
  private String insu_status;
}
