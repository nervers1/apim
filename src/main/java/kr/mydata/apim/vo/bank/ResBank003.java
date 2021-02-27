package kr.mydata.apim.vo.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 수신계좌 추가정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBank003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String detail_cnt;
    private List<ResBank003Sub> detail_list;
}
