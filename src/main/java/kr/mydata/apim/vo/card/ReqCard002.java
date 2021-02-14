package kr.mydata.apim.vo.card;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.mydata.apim.vo.APIEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 카드 기본정보 조회 - 입력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReqCard002 extends APIEntity {
  private String authorization;
  private String org_code;
  private String search_timestamp;
}
