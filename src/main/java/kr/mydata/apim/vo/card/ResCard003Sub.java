package kr.mydata.apim.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 포인트 정보 조회 - 입력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCard003Sub {
    private String point_type;
    private String remain_point_amt;
    private String expiring_point_amt;
}
