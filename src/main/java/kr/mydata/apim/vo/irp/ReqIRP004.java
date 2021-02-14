package kr.mydata.apim.vo.irp;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통) - 입력
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqIRP004 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String from_dtime;
  private String to_dtime;
  private String next_page;
  private int limit;

}
