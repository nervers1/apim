package kr.mydata.apim.vo.invest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 계좌 목록 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInvest001Sub {
    private String account_num;
    private String is_consent;
    private String account_name;
    private String account_type;
    private String account_status;
}
