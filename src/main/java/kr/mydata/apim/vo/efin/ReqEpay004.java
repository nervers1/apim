package kr.mydata.apim.vo.efin;

import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 선불 거래내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqEpay004 extends APIEntity {
  private String authorization;
  private String org_code;
  private String sub_key;
  private String from_dtime;
  private String to_dtime;
  private ResEpay004Sub next_page;
  private int limit;
}
