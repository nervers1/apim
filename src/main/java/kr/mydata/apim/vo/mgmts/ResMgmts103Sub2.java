package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 정보주체 별 전송요구 내역 조회 - 전송요구 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMgmts103Sub2 {
    private String consent_type;
    private String consent_date;
    private String consent_exp_date;
}
