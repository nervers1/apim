package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias({"approval-cnt", "approved_cnt"})
    private int approved_cnt;
    @JsonAlias({"approval-list", "approved_list"})
    private List<ResCard007Sub> approved_list;
}
