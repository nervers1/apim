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
            String mydata_org_code = "";
            String type = "";
            String client_id = "";
            String inquiry_date = "";
            String stat_date_cnt = "";
            Map<String, Object> stat_date_list = new HashMap<>();

            String stat_date = "";
            String org_cnt = "";
            Map<String, Object> org_list = new HashMap<>();

            String org_code = "";
            String token_new = "";
            String token_renew = "";
            String token_revoke = "";
            String token_own = "";
            String api_type_cnt = "";
            Map<String, Object> api_type_list = new HashMap<>();

            String api_type = "";
            String tm_slot_cnt = "";
            Map<String, Object> tm_slot_list = new HashMap<>();

            String tm_slot = "";
            String rsp_med = "";
            String rsp_avg = "";
            String rsp_total = "";
            String rsp_stdev = "";
            String status_cnt = "";
            Map<String, Object> status_list = new HashMap<>();

            String status_code = "";
            String api_call_cnt = "";

            Map<String, Object> params = new HashMap<>();
            params.put("mydata_org_code", mydata_org_code);
            params.put("type", type);
            params.put("client_id", client_id);
            params.put("inquiry_date", inquiry_date);
            params.put("stat_date_cnt", stat_date_cnt);
            params.put("stat_date_list", stat_date_list);

            stat_date_list.put("stat_date", stat_date);
            stat_date_list.put("org_cnt", org_cnt);
            stat_date_list.put("org_list", org_list);

            org_list.put("org_code", org_code);
            org_list.put("token_new", token_new);
            org_list.put("token_renew", token_renew);
            org_list.put("token_revoke", token_revoke);
            org_list.put("token_own", token_own);
            org_list.put("api_type_cnt", api_type_cnt);
            org_list.put("api_type_list", api_type_list);

            api_type_list.put("api_type", api_type);
            api_type_list.put("tm_slot_cnt", tm_slot_cnt);
            api_type_list.put("tm_slot_list", tm_slot_list);

            tm_slot_list.put("tm_slot", tm_slot);
            tm_slot_list.put("rsp_med", rsp_med);
            tm_slot_list.put("rsp_avg", rsp_avg);
            tm_slot_list.put("rsp_total", rsp_total);
            tm_slot_list.put("rsp_stdev", rsp_stdev);
            tm_slot_list.put("status_cnt", status_cnt);
            tm_slot_list.put("status_list", status_list);

            status_list.put("status_code", status_code);
            status_list.put("api_call_cnt", api_call_cnt);

            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(params);

            HttpEntity<?> entity = new HttpEntity<>(body, header);

            String url = "http://localhost:7080/v1/mgmts/statistics"; // 호출 API의 URL
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


