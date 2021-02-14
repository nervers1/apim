package kr.mydata.apim.vo.efin;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 전자지급수단 목록 조회 - 출력
 */
@Data
public class ResEpay001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private String name;
  private int account_cnt;
  private List<ResEpay001Sub> account_list;
}
