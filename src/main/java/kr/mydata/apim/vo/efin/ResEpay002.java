package kr.mydata.apim.vo.efin;

import lombok.Data;

import java.util.List;

/**
 * 전자지급수단 잔액정보 조회 - 출력
 */
@Data
public class ResEpay002 {
  private String rsp_code;
  private String rsp_msg;
  private String search_timestamp;
  private int fob_cnt;
  private List<ResEpay002Sub> fob_list;
}
