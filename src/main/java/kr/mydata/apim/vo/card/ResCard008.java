package kr.mydata.apim.vo.card;


import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias({"approval_cnt", "approval-cnt"})
    private int approved_cnt; // 오타수정
    @JsonAlias({"approved_list", "approval-list"})
    private List<ResCard008Sub> approved_list; // 오타수정
}
