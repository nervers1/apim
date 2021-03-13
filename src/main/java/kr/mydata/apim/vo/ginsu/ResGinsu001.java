package kr.mydata.apim.vo.ginsu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 보증보험 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResGinsu001 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String insu_cnt;
    private List<ResGinsu001Sub> insu_list;
}
