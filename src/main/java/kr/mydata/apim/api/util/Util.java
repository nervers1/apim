package kr.mydata.apim.api.util;

import org.springframework.util.ObjectUtils;

public class Util {
    /**
     * 요청메시지의 next_page 를 int 형으로 return
     *
     * @param next_page 요청메시지의 next_page
     * @return 요청메시지에 next_page가 없는 경우 0 리턴, 있는 경우 int 형변환하여 리턴
     */
    public static int getPage(String next_page) {
        if (ObjectUtils.isEmpty(next_page))
            return 0;
        return Integer.parseInt(next_page);
    }

    /**
     * 응답메시지의 next_page 를 생성
     *
     * @param trans_cnt DB에 저장된 데이터의 전체 list 갯수
     * @param page      요청메시지의 next_page
     * @param limit     요청메시지의 limit
     * @return 추가조회 데이터가 있는 경우 next_page 값, 없는 경우 null
     */
    public static String getNextPage(int trans_cnt, int page, int limit) {
        if (trans_cnt <= page + limit)
            return null;
        else
            return String.valueOf(page + limit);
    }
}
