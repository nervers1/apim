package kr.mydata.apim.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

  final JdbcTemplate jdbcTemplate;

  public CardServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


}
