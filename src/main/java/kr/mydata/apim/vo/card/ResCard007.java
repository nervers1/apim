package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 국내 승인내역 조회 - 출력
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCard007 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String approved_cnt;
    private List<ResCard007Sub> approved_list;
}
