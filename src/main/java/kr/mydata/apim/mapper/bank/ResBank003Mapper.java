package kr.mydata.apim.mapper.bank;

import kr.mydata.apim.vo.bank.ResBank003;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResBank003Mapper implements RowMapper<ResBank003> {

  public ResBank003 mapRow(ResultSet rs, int rowNum) throws SQLException {
    ResBank003 entity = new ResBank003();
    entity.setRsp_code(rs.getString("rsp_code"));
    entity.setRsp_msg(rs.getString("rsp_msg"));
    entity.setSearch_timestamp(rs.getString("search_timestamp"));
    entity.setCurrent_balance_amt(new BigDecimal(rs.getString("current_balance_amt")));
    entity.setWithdrawable_amt(new BigDecimal(rs.getString("withdrawable_amt")));
    entity.setOffered_rate(rs.getDouble("offered_rate"));
    entity.setLast_paid_in_cnt(rs.getInt("last_paid_in_cnt"));
    return entity;
  }

}
