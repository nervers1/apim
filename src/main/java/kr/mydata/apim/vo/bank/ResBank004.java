package kr.mydata.apim.vo.bank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 수신계좌 거래내역 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResBank004 {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String trans_cnt;
    private List<ResBank004Sub> trans_list;
}
