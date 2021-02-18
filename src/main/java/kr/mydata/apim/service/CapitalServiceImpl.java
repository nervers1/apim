package kr.mydata.apim.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.loan.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CapitalServiceImpl implements CapitalService {

  public CapitalServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final ObjectMapper mapper = new ObjectMapper();
  private final JdbcTemplate jdbcTemplate;

  /**
   * 할부금융 업권 - 계좌 목록 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResLoan001 loans(ReqLoan001 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResLoan001.class);
  }

  /**
   * 할부금융 업권 - 대출상품계좌 기본정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResLoan002 basic(ReqLoan002 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResLoan002.class);
  }

  /**
   * 할부금융 업권 - 대출상품계좌 추가정보 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResLoan003 detail(ReqLoan003 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    return mapper.readValue(res, ResLoan003.class);
  }

  /**
   * 할부금융 업권 - 대출상품계좌 거래내역 조회
   * @param req
   * @param api_id
   * @param own_org_cd
   * @return
   * @throws JsonProcessingException
   */
  @Override
  public ResLoan004 transactions(ReqLoan004 req, String api_id, String own_org_cd) throws JsonProcessingException {
    String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
    String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
    // to JSON
    mapper.registerModule(new JavaTimeModule());
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    ResLoan004 resVo = mapper.readValue(res, ResLoan004.class);
    List<ResLoan004Sub> trans_list = resVo.getTrans_list();
    
    int page = Util.getPage(req.getNext_page());
    trans_list = trans_list.stream()
    		.skip(req.getLimit() * (page - 1))
    		.limit(req.getLimit())
    		.collect(Collectors.toList());
    resVo.setTrans_list(trans_list);
    resVo.setNext_page(Util.getNextPage(resVo.getTrans_cnt(), page, req.getLimit()));
    return resVo;
  }
}
