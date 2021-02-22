package kr.mydata.apim.vo.efin;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.mydata.apim.vo.bank.ResBank004Sub;
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
public class ResEfin005 implements Comparable<ResEfin005> {
    private String rsp_code;
    private String rsp_msg;
    private String next_page;
    private String trans_cnt;
    private List<ResEfin005Sub> trans_list;
    
    @Override
    public int compareTo(ResEfin005 o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_cnt.compareTo(this.trans_cnt);
    }
}
