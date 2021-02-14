package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kr.mydata.apim.mapper.APIMapper;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

/**
 * 수신계좌 거래내역 조회 - 입력
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
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
