package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class CallAPI {

	public String callGetAPI() {

		HashMap<String, Object> result = new HashMap<String, Object>();

		String resultBody = "";

		try {
			// 연결설정
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(3000);
			factory.setReadTimeout(3000);

			RestTemplate restTemplate = new RestTemplate(factory);

			// http header 설정
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", "Bearer [접근토큰]"); // 인증API로 생성한 접근토큰 설정
			header.add("X-FSI-SVC-DATA-KEY", "N"); // N : 금보원제공 샘플데이터 이용, Y : 업로드데이터 이용

			// request parameter 설정
			String org_code = "0000000";
			String search_timestamp = "0";
			String limit = "20";

			StringBuilder parameter = new StringBuilder();
			parameter.append("?" + "org_code=" + org_code);
			parameter.append("&" + "search_timestamp=" + search_timestamp);
			parameter.append("&" + "limit=" + limit);

			HttpEntity<?> entity = new HttpEntity<>(header);

			String url = "http://localhost:7080/bank/accounts"; // 호출 API의 URL
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + parameter).build();

			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
			result.put("statusCode", resultMap.getStatusCodeValue());
			result.put("header", resultMap.getHeaders());
			result.put("body", resultMap.getBody());

			ObjectMapper mapper = new ObjectMapper();
			resultBody = mapper.writeValueAsString(resultMap.getBody());

			System.out.println("result: " + resultMap);
			System.out.println("result body : " + resultBody);

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return resultBody;
	}

	public String callPostAPI() {

		HashMap<String, Object> result = new HashMap<String, Object>();

		String resultBody = "";

		try {
			// 연결설정
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setConnectTimeout(3000);
			factory.setReadTimeout(3000);

			RestTemplate restTemplate = new RestTemplate(factory);

			// http header 설정
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", "Bearer [접근토큰]"); // 인증API로 생성한 접근토큰 설정
			header.add("X-FSI-SVC-DATA-KEY", "N"); // N : 금보원제공 샘플데이터 이용, Y : 업로드데이터 이용
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

			// request parameter 설정
			String org_code = "0000000";

			Map<String, Object> params = new HashMap<>();
			params.put("org_code", "0000000");
			params.put("account_num", "09830487129182");
			params.put("seqno", "000");
			params.put("currency_code", "KRW");
			params.put("search_timestamp", "0");

			ObjectMapper objectMapper = new ObjectMapper();
			String body = objectMapper.writeValueAsString(params);

			HttpEntity<?> entity = new HttpEntity<>(body, header);

			String url = "http://localhost:7080/bank/accounts/deposit/basic"; // 호출 API의 URL
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

			ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Map.class);
			result.put("statusCode", resultMap.getStatusCodeValue());
			result.put("header", resultMap.getHeaders());
			result.put("body", resultMap.getBody());

			ObjectMapper mapper = new ObjectMapper();
			resultBody = mapper.writeValueAsString(resultMap.getBody());

			System.out.println("result: " + resultMap);
			System.out.println("result body : " + resultBody);

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return resultBody;
	}
}


