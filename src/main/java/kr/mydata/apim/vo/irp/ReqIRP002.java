package kr.mydata.apim.vo.irp;


import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 개인형 IRP 계좌 기본정보 조회 (은행, 금투, 모험 공통) - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqIRP002 extends APIEntity {
  private String authorization;
  private String org_code;
  private String account_num;
  private String search_timestamp;
}
