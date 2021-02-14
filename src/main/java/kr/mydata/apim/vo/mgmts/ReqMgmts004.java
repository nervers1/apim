package kr.mydata.apim.vo.mgmts;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * 통계자료 전송 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqMgmts004 extends APIEntity {
  private String authorization;
  private String mydata_org_code;
  private String type;
  private String client_id;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate inquiry_date;
  private int statistics_date_cnt;
  private List<ReqMgmts004Sub> statistics_date_list;
}
