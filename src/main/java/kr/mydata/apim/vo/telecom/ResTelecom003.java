package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 통신 거래내역 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom003 {
    private String rsp_code;
    private String rsp_msg;
    private String trans_cnt;
    private List<ResTelecom003Sub> trans_list;
}
