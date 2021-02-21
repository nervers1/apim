package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 해외 승인내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard008 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String approved_cnt;
    private List<ResCard008Sub> approved_list;
}
