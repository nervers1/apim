package kr.mydata.apim.vo.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 단기대출 정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard011 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String short_term_cnt;
    private List<ResCard011Sub> short_term_list;
}
