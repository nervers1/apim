package kr.mydata.apim.vo.irp;

import kr.mydata.apim.vo.bank.ResBank004Sub;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 개인형 IRP 계좌 거래내역 조회 (은행, 금투, 모험 공통) - 항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP004Sub implements Comparable<ResIRP004Sub> {
    private String trans_dtime;
    private String trans_type;
    private String trans_amt;

    @Override
    public int compareTo(ResIRP004Sub o) {
        // 거래일자 기준 내림차순 정렬
        return o.trans_dtime.compareTo(this.trans_dtime);
    }
}
