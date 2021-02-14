package kr.mydata.apim.vo.irp;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 개인형 IRP 계좌 목록 조회 (은행, 금투, 모험 공통) - 출력
 */
@Data
public class ResIRP001 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int irp_cnt;
  private List<ResIRP001Sub> irp_list;
}
