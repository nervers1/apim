package kr.mydata.apim.vo.efin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 결제내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResEfin005 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private int trans_cnt;
    private List<ResEfin005Sub> trans_list;
}
