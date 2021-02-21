package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 결제정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard006 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String pay_cnt;
    private List<ResCard006Sub> pay_list;
}
