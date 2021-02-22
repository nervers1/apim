package kr.mydata.apim.vo.efin;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 선불 거래내역 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ReqEfin004{
    private String authorization;
    private String org_code;
    private String sub_key;
    private String from_dtime;
    private String to_dtime;
    private String next_page;
    private String limit;
}
