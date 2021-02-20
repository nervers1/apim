package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 보험 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu001 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private int insu_cnt;
    private List<ResInsu001Sub> insu_list;
}
