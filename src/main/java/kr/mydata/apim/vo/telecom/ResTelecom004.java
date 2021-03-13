package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 결제 내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom004 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String trans_cnt;
    private List<ResTelecom004Sub> trans_list;
}
