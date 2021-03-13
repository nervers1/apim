package kr.mydata.apim.vo.ginsu;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 보증보험 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResGinsu003 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String trans_cnt;
    private List<ResGinsu003Sub> trans_list;
}