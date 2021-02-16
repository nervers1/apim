package kr.mydata.apim.vo.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 국내 승인내역 조회 - 출력
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCard007 {
	private String rsp_code;
	private String rsp_msg;
	private String next_page;
	@JsonAlias({ "approval-cnt", "approved_cnt" })
	private String approved_cnt;
	@JsonAlias({ "approval-list", "approved_list" })
	private List<ResCard007Sub> approved_list;
}
