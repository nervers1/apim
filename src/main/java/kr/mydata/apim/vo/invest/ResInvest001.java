package kr.mydata.apim.vo.invest;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * 계좌 목록 조회 - 출력
 */
@Data
public class ResInvest001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate reg_date;
  private int account_cnt;
  private List<ResInvest001Sub> account_list;
}
