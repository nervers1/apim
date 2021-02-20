package kr.mydata.apim.vo.insu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보험 기본정보 조회 - 상세
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResInsu002Sub {
    private String insured_no;
    private String insured_name;
}
