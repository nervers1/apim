package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 청구 정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom002Sub {
    private String mgmt_id;
    private String charge_amt;
    private String charge_date;
}
