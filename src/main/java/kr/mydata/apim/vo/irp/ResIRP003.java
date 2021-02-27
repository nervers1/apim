package kr.mydata.apim.vo.irp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 개인형 IRP 계좌 추가정보 조회 (은행, 금투, 모험 공통) - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP003 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String next_page;
    private String irp_cnt;
    private List<ResIRP003Sub> irp_list;
}
