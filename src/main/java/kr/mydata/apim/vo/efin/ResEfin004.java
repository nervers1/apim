package kr.mydata.apim.vo.efin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 선불 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResEfin004 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private int trans_cnt;
    private List<ResEfin004Sub> trans_list;
}
