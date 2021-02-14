package kr.mydata.apim.vo.common;

import lombok.Data;

import java.util.List;

/**
 * API 목록 조회 (공통)
 * api: /apis
 * Header:
 * - Authorization 접근토큰
 * Parameter:
 * - org_code   기관코드   aN(7)    - 지원 API로부터 배포
 * Body:
 * - rsp_code   세부 응답코드 aN(5)
 * - rsp_msg    세부응답메시지 AH(300)
 * - version    현재 버전 aN(10)    - API 현재 버전
 * - min_version    호환가능 혅현재 버전 aN(10)    - API 현재 버전
 * - api_cnt    API 개수 N(2)      - 제공 API 개수
 * - api_list   API 목록           - <Object>
 * - api_uri    API 명 aN(50)      - URI 계층 구조 중 <base path>를 제외한 URI 정보(ex /v5/bank/accounts/basic)
 */
@Data
public class ResCmn001 {
  private String rsp_code;
  private String rsp_msg;
  private String version;
  private String min_version;
  private int api_cnt;
  private List<ResCmn001Sub> api_list;
}
