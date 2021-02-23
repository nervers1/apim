package kr.mydata.apim.vo.mgmts;

import kr.mydata.apim.vo.APIEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 통계자료 전송 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqMgmts004{
	@NotNull(message = "authorization 값이 반드시 있어야 합니다.")
    private String authorization;
	
	@NotNull(message = "org_code 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 7, message = "org_code 값은 1 ~ 7 자리 입니다.")
    private String mydata_org_code;
	
	@NotNull(message = "type 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 1, message = "type 값은 1 자리 입니다.")
    private String type;
	
	@NotNull(message = "client_id 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 50, message = "client_id 값은 1 ~ 50 자리 입니다.")
    private String client_id;
	
	@NotNull(message = "inquiry_date 값이 반드시 있어야 합니다.")
    @Size(min = 14, max = 14, message = "inquiry_date 값은 14 자리 입니다.")
    private String inquiry_date;
	
	@NotNull(message = "statistics_date_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 1, message = "statistics_date_cnt 값은 1 자리 입니다.")
    private String statistics_date_cnt;
	
    private List<ReqMgmts004Sub> statistics_date_list;
}
