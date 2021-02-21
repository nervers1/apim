package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 포인트 정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String point_cnt;
    private List<ResCard003Sub> point_list;
}
