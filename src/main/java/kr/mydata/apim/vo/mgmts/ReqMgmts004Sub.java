package kr.mydata.apim.vo.mgmts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 통계자료 전송 - 통계 일자별 통계정보 항목
 */
@Data
public class ReqMgmts004Sub {
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate statistics_date;
  private int org_cnt;
  private List<ReqMgmts004Sub2> org_list;
}
