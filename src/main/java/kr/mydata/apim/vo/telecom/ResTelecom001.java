package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통신 계약 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom001 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String telecom_cnt;
    private List<ResTelecom001Sub> telecom_list;
}
