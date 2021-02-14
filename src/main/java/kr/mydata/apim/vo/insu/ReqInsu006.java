package kr.mydata.apim.vo.insu;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 보험 거래내역 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqInsu006 extends APIEntity {
  private String authorization;
  private String org_code;
  private String insu_num;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate from_date;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate to_date;
  private ResInsu006Sub next_page;
  private int limit;
}
