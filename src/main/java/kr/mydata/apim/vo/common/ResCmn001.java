package kr.mydata.apim.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCmn001 {
	private String rsp_code;
	private String rsp_msg;
	private String version;
	private String min_version;
	private String api_cnt;
	private List<ResCmn001Sub> api_list;
}
