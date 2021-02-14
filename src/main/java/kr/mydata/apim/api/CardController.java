package kr.mydata.apim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.service.APIService;
import kr.mydata.apim.vo.card.ReqCard001;
import kr.mydata.apim.vo.card.ReqCard002;
import kr.mydata.apim.vo.card.ReqCard007;
import kr.mydata.apim.vo.card.ResCard001;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class CardController {

  private final APIService service;
  private final ObjectMapper mapper = new ObjectMapper();
  final JdbcTemplate jdbcTemplate;

  public CardController(APIService service, JdbcTemplate jdbcTemplate) {
    this.service = service;
    this.jdbcTemplate = jdbcTemplate;
  }


  /**
   * 카드업권 - 카드 목록 조회
   *
   * @param api_id
   * @param req
   * @return
   */
  @GetMapping(value = "/v1/card/cards", produces = "application/json; charset=UTF-8")
  public ResponseEntity listCards(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  @ModelAttribute ReqCard001 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id;
    try {
      // Card
      String res = jdbcTemplate.queryForObject(sql, String.class);
      // to JSON
      mapper.registerModule(new JavaTimeModule());
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      ResCard001 resCard001 = mapper.readValue(res, ResCard001.class);

      return new ResponseEntity(resCard001, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 - 카드 기본정보 조회
   *
   * @param api_id
   * @param req
   * @return http://localhost:8080/v1/card/cards/11345678923?org_code=0000000&search_timestamp=20210101221111
   */
  @GetMapping(value = "/v1/card/cards/{card_id}", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardBasic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  @PathVariable(value = "card_id") String card_id,
                                  ReqCard002 req) throws JsonProcessingException {

    log.info("api_id : {}", api_id);
    log.info("card_id : {}", card_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    Map<String, String> search_timestamp = new HashMap<>();
    search_timestamp.put("search_timestamp", req.getSearch_timestamp());
    String strTimeStamp = mapper.writeValueAsString(search_timestamp);
//    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = "+ api_id + " and res_data @> '{\"search_timestamp\":\"" + req.getSearch_timestamp() + "\"}'::jsonb";
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and res_data @> '" + strTimeStamp + "'::jsonb";
    try {
      // Card
      String res = jdbcTemplate.queryForObject(sql, String.class);
      log.info("res : {}", res);
      // to JSON
      mapper.registerModule(new JavaTimeModule());
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 카드업권 - 국내 승인내역 조회
   *
   * @param api_id
   * @param req
   * @return http://localhost:8080/v1/card/cards/10456243512223/approval-domestic?org_code=0000000&from_dtime=20210101000000&to_dtime=20210501235959&limit=20
   */
  @GetMapping(value = "/v1/card/cards/{card_id}/approval-domestic", produces = "application/json; charset=UTF-8")
  public ResponseEntity cardApprovalDomestic(@RequestHeader(value = "x-api-id") String api_id, @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                             @PathVariable(value = "card_id") String card_id,
                                             ReqCard007 req) throws JsonProcessingException {

    log.info("api_id : {}", api_id);
    log.info("card_id : {}", card_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id;
    try {
      // Card
      String res = jdbcTemplate.queryForObject(sql, String.class);
      log.info("res : {}", res);
      // to JSON
      mapper.registerModule(new JavaTimeModule());
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      return new ResponseEntity(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
