package kr.mydata.apim.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인가코드 발급 요청 - 오류
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResAuth001Error {
    private String error;
    private String error_description;
}
