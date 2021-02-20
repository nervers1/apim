package kr.mydata.apim.vo.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 청구 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResCard004 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private int bill_cnt;
    private List<ResCard004Sub> bill_list;
}
