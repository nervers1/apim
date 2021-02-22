package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 보험 특약정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String contract_cnt;
    private List<ResInsu003Sub> contract_list;
}
