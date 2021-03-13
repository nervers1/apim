package kr.mydata.apim.vo.ginsu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 보증보험 기본정보 조회 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResGinsu002 {
    private String rsp_code;
    private String rsp_msg;
    private String search_timestamp;
    private String issue_date;
    private String exp_date;
    private String face_amt;
    private String pay_due;
    private String pay_amt;
    private String insured_count;
    private List<ResGinsu002Sub> insured_list;
}
