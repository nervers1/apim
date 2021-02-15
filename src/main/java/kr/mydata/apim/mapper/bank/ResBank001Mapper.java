package kr.mydata.apim.mapper.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mydata.apim.vo.bank.ResBank001;
import kr.mydata.apim.vo.bank.ResBank001Sub;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResBank001Mapper implements RowMapper<ResBank001> {
  @Override
  public ResBank001 mapRow(ResultSet rs, int rowNum) throws SQLException {
      ResBank001 entity = new ResBank001();
      log.info("rs --> {}", rs);
    try {
      entity.setRsp_code(rs.getString("rsp_code"));
      entity.setRsp_msg(rs.getString("rsp_msg"));
      entity.setSearch_timestamp(rs.getString("search_timestamp"));
      entity.setReg_date(rs.getString("issue_date"));
      entity.setAccount_cnt(rs.getString("account_cnt"));
      String accounts = rs.getString("account_list");
      ObjectMapper mapper = new ObjectMapper();
      List<ResBank001Sub> account_list = mapper.readValue(accounts, mapper.getTypeFactory().constructCollectionType(ArrayList.class, ResBank001Sub.class));
      log.info("list --> {}", account_list);
      entity.setAccount_list(account_list);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return entity;
  }
}
