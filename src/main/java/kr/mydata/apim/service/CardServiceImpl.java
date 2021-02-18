package kr.mydata.apim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.card.*;
import kr.mydata.apim.vo.efin.ResEfin004;
import kr.mydata.apim.vo.efin.ResEfin004Sub;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Log4j2
@Service
public class CardServiceImpl implements CardService {

  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;

  public CardServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * 카드업권 : 카드 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard001 listCard(ReqCard001 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id;
    // Card
    String res = jdbcTemplate.queryForObject(sql, String.class);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResCard001 resCard001 = mapper.readValue(res, ResCard001.class);
    return resCard001;
  }

  /**
   * 카드업권 : 카드 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param card_id
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard002 cardBasic(ReqCard002 req, String api_id, String own_org_cd, String card_id) throws JsonProcessingException {
    Map<String, String> search_timestamp = new HashMap<>();
    search_timestamp.put("search_timestamp", req.getSearch_timestamp());
    String strTimeStamp = mapper.writeValueAsString(search_timestamp);
//    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = " + api_id + " and res_data @> '" + strTimeStamp + "'::jsonb";
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and ast_id = ?";

    // Card
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, card_id);
    log.info("res : {}", res);

    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    ResCard002 resCard002 = mapper.readValue(res, ResCard002.class);
    return resCard002;
  }

  /**
   * 카드업권 : 포인트 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard003 cardPoint(ReqCard003 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard003.class);
  }

  /**
   * 카드업권 : 청구 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard004 cardBills(ReqCard004 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResCard004 ResCard004 = mapper.readValue(res, ResCard004.class);
    
    List<ResCard004Sub> trans_list = ResCard004.getBill_list();
        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
        		.skip(req.getLimit() * (page - 1))
        		.limit(req.getLimit())
        		.collect(Collectors.toList());
        ResCard004.setBill_list(trans_list);
        ResCard004.setNext_page(Util.getNextPage(ResCard004.getBill_cnt(), page, req.getLimit()));
        return ResCard004;
}
  

  /**
   * 카드업권 : 청구 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard005 cardBillsDetail(ReqCard005 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    
    ResCard005 ResCard005 = mapper.readValue(res, ResCard005.class);
    
    List<ResCard005Sub> trans_list = ResCard005.getBill_detail_list();
        int page = Util.getPage(req.getNext_page());
        trans_list = trans_list.stream()
        		.skip(req.getLimit() * (page - 1))
        		.limit(req.getLimit())
        		.collect(Collectors.toList());
        ResCard005.setBill_detail_list(trans_list);
        ResCard005.setNext_page(Util.getNextPage(ResCard005.getBill_detail_cnt(), page, req.getLimit()));
        return ResCard005;
  }

  /**
   * 카드업권 : 결제정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard006 cardPayment(ReqCard006 req, String api_id, String own_org_cd) throws JsonProcessingException {

    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard006.class);
  }

  /**
   * 카드업권 : 국내 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param card_id
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard007 cardApprovalDomestic(ReqCard007 req, String api_id, String own_org_cd, String card_id) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), card_id);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard007.class);
  }

  /**
   * 카드업권 : 해외 승인내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @param card_id
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard008 cardApprovalOverseas(ReqCard008 req, String api_id, String own_org_cd, String card_id) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), card_id);
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard008.class);
  }

  /**
   * 카드업권 : 대출상품 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard009 cardLoans(ReqCard009 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard009.class);
  }

  /**
   * 카드업권 : 리볼빙 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard010 cardLoansRevolving(ReqCard010 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard010.class);
  }

  /**
   * 카드업권 : 단기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard011 cardLoansShortTerm(ReqCard011 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard011.class);
  }

  /**
   * 카드업권 : 장기대출 정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResCard012 cardLoansLongTerm(ReqCard012 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResCard012.class);
  }
}
