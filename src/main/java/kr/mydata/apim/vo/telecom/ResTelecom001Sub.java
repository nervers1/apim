package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 통신 계약 목록 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class

ResTelecom001Sub {
    private String mgmt_id;
    private String is_consent;
    private String telecom_num;
    private String type;
    private String status;
}
