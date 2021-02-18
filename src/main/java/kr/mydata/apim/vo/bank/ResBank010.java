package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 대출상품계좌 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResBank010 {
  private String rsp_code;                     // 세부 응답코드
  private String rsp_msg;                      // 세부 응답메시지
  private String next_page;             // 다음 페이지 기준개체
  private int trans_cnt;                       // 거래목록수
  private List<ResBank010Sub> trans_list;      // 거래목록
}
