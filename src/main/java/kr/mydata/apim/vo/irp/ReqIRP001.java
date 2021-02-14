package kr.mydata.apim.vo.irp;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 개인형 IRP 계좌 목록 조회 (은행, 금투, 모험 공통) - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ReqIRP001 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
