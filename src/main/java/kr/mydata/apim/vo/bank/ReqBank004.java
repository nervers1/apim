package kr.mydata.apim.vo.bank;

import kr.mydata.apim.mapper.APIMapper;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 수신계좌 거래내역 조회 - 입력
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqBank004 extends APIMapper {

    @NotNull(message = "org_code is null")
    @Size(min = 1, max = 7, message = "org_code size error")
    private String org_code;

    @NotNull(message = "account_num is null")
    @Size(min = 1, max = 20, message = "account_num size error")
    private String account_num;

    @Size(min = 1, max = 5, message = "seqno size error")
    private String seqno;

    @Size(min = 3, max = 3, message = "currency_code size error")
    private String currency_code;

    @NotNull(message = "from_dtime is null")
    @Size(min = 14, max = 14, message = "from_dtime size error")
    private String from_dtime;

    @NotNull(message = "to_dtime is null")
    @Size(min = 14, max = 14, message = "to_dtime size error")
    private String to_dtime;

    @Size(min = 1, max = 1000, message = "next_page size error")
    private String next_page;

    @NotNull(message = "limit is null")
    @Size(min = 1, max = 3, message = "limit size error")
    private String limit;
}
