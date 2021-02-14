package kr.mydata.apim.vo.bank;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 대출상품계좌 거래내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqBank010 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String from_dtime;
  private String to_dtime;
  private ResBank010 next_page;
  private int limit;
}
