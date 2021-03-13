package kr.mydata.apim.vo.telecom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 청구 정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResTelecom002 {
    private String rsp_code;
    private String rsp_msg;
    private String bill_cnt;
    private List<ResTelecom002Sub> bill_list;
}
