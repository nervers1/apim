package kr.mydata.apim.vo.capital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 대출상품계좌 거래내역 조회 - 이자적용항목
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCapital004Sub2 {
    private String int_start_date;
    private String int_end_date;
    private String int_rate;
    private String int_type;
}