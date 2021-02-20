package kr.mydata.apim.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 인가코드 발급 요청 - 출력
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResAuth001 {
    private String code;
    private String state;
}
