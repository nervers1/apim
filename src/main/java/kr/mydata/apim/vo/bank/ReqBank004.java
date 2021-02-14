package kr.mydata.apim.vo.bank;

import kr.mydata.apim.mapper.APIMapper;
import lombok.*;

/**
 * 수신계좌 거래내역 조회 - 입력
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank004 extends APIMapper {
  private String authorization;
  private String org_code;
  private String account_num;
  private int seqno;
  private String currency_code;
  private String from_dtime;
  private String to_dtime;
  private ResBank004Sub next_page;
}
