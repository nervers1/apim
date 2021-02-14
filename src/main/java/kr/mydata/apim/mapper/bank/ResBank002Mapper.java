package kr.mydata.apim.mapper.bank;

import kr.mydata.apim.vo.bank.ResBank002;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResBank002Mapper implements RowMapper<ResBank002> {

  public ResBank002 mapRow(ResultSet rs, int rowNum) throws SQLException {
    ResBank002 entity = new ResBank002();
    entity.setRsp_code(rs.getString("rsp_code"));
    entity.setRsp_msg(rs.getString("rsp_msg"));
    entity.setSearch_timestamp(rs.getString("search_timestamp"));
    entity.setSaving_method(rs.getString("saving_mehotd"));
    entity.setHolder_name(rs.getString("holder_name"));
    entity.setIssue_date(rs.getString("issue_date"));
    entity.setExp_date(rs.getString("exp_date"));
    entity.setCurrency_code(rs.getString("currency_code"));
    entity.setCommit_amt(rs.getBigDecimal("commit_amt"));
    entity.setMonthly_paid_in_amt(rs.getBigDecimal("monthly_paid_in_amt"));
    entity.setTermination_amt(rs.getBigDecimal("termination_amt"));
    entity.setLast_offered_rate(rs.getDouble("last_offered_rate"));
    return entity;
  }

}
