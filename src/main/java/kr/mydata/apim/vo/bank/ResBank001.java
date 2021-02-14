package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * 계좌 목록 조회 - 출력
 */
@Data
public class ResBank001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  @JsonDeserialize(as = LocalDate.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate reg_date;
  private int account_cnt;
  private List<ResBank001Sub> account_list;
}
