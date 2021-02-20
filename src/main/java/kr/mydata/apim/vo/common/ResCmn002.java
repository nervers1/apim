package kr.mydata.apim.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전송요구 내역 조회 (공통) - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCmn002 {
    private String rsp_code;
    private String rsp_msg;
    private String is_scheduled;
    private String cycle;
    private String end_date;
    private String purpose;
    private String period;
}
