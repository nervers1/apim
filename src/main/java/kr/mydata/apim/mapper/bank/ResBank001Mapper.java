package kr.mydata.apim.mapper.bank;

import kr.mydata.apim.vo.bank.ResBank001;
import lombok.extern.log4j.Log4j2;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
public class ResBank001Mapper implements RowMapper<ResBank001> {
  @Override
  public ResBank001 mapRow(ResultSet rs, int rowNum) throws SQLException {
    ResBank001 entity = new ResBank001();
    entity.setRsp_code(rs.getString("rsp_code"));
    entity.setRsp_msg(rs.getString("rsp_msg"));
    entity.setSearch_timestamp(rs.getString("search_timestamp"));
    entity.setReg_date(Optional.ofNullable(rs.getTimestamp("issue_date")).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate).orElse(null));
    entity.setAccount_cnt(rs.getInt("account_cnt"));
    String account_list = ((PGobject) rs.getObject("account_list")).getValue();

    log.info("list --> {}", account_list);
    return entity;
  }
}
