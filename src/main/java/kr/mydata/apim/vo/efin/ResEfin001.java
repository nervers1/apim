package kr.mydata.apim.vo.efin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 전자지급수단 목록 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResEfin001 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String name;
    private String reg_date;
    private String account_cnt;
    private List<ResEfin001Sub> account_list;
}
