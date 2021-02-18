package kr.mydata.apim.api.util;

import org.springframework.util.ObjectUtils;

public class Util {
	public static int getPage(String next_page) {
		if(ObjectUtils.isEmpty(next_page))
			return 1;
		return Integer.parseInt(next_page);
	}

	public static String getNextPage(int trans_cnt, int page, int limit) {
		int lastPage = (trans_cnt / limit) + 1;

		if(trans_cnt < limit * (page - 1) 
				|| lastPage == page)
			return String.valueOf(lastPage);
		return String.valueOf(++page);
	}
}
