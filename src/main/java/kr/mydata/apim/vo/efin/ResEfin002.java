package kr.mydata.apim.vo.efin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 전자지급수단 잔액정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private int fob_cnt;
    private List<ResEfin002Sub> fob_list;
}
