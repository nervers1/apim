package kr.mydata.apim.vo.card;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 포인트 정보 조회 - 입력
 */
@Data
public class ResCard003 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int point_cnt;
  private List<ResCard003Sub> point_list;
}
