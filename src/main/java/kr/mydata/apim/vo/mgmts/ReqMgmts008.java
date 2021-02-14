package kr.mydata.apim.vo.mgmts;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 통계자료 재전송 요청 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqMgmts008 extends APIEntity {
  private String authorization;
  private String mydata_org_code;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate inquiry_date;
}
