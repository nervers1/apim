package kr.mydata.apim.mapper.card;

import kr.mydata.apim.vo.card.ResCard002;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ResCard002Mapper implements RowMapper<ResCard002> {
  @Override
  public ResCard002 mapRow(ResultSet rs, int rowNum) throws SQLException {
    ResCard002 entity = new ResCard002();
    entity.setRsp_code(rs.getString("rsp_code"));
    entity.setRsp_msg(rs.getString("rsp_msg"));
    entity.setSearch_timestamp(rs.getString("search_timestamp"));
    entity.setCard_type(rs.getString("card_type"));
    entity.set_trans_payable("true".equals(rs.getString("is_trans_payable")));
    entity.set_cash_card("true".equals(rs.getString("is_cash_card")));
    entity.setLinked_bank_code(rs.getString("linked_bank_code"));
    entity.setAnnual_fee(rs.getBigDecimal("annual_fee"));
    entity.setIssue_date(LocalDate.parse(rs.getString("issue_date")));
    return entity;
  }
}
