package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 카드 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard001 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String card_cnt;
    private List<ResCard001Sub> card_list;
}
