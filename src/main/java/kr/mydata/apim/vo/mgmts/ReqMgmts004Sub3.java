package kr.mydata.apim.vo.mgmts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 통계자료 전송 - API 구분 별 통계정보 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMgmts004Sub3 {

    @NotNull(message = "api_type 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 4, message = "api_type 값은 1 ~ 4 자리 입니다.")
    private String api_type;

    @NotNull(message = "tm_slot_cnt 값이 반드시 있어야 합니다.")
    @Size(min = 1, max = 2, message = "tm_slot_cnt 값은 1 ~ 2 자리 입니다.")
    private String tm_slot_cnt;

    @NotNull(message = "tm_slot_list 값이 반드시 있어야 합니다.")
    private List<ReqMgmts004Sub4> tm_slot_list;
}
