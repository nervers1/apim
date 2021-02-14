package kr.mydata.apim.mapper;

import kr.mydata.apim.vo.APIEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class APIMapper implements RowMapper<APIEntity> {
  public APIEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    APIEntity entity = new APIEntity();
    entity.setSeq_no(rs.getInt("seq_no"));
    entity.setBusr(rs.getInt("busr"));
    entity.setApi_id(rs.getInt("api_id"));
    entity.setOrg_cd(rs.getString("org_cd"));
    entity.setOwn_org_cd(rs.getString("own_org_cd"));
    entity.setAst_id(rs.getString("ast_id"));
    entity.setScope(rs.getString("scope"));
    entity.setRes_data(rs.getString("res_data"));
    entity.setApi_ver(rs.getInt("api_ver"));
    entity.setData_nm(rs.getString("data_nm"));
    Optional.ofNullable(rs.getTimestamp("reg_dt")).map(Timestamp::toLocalDateTime).orElse(null);
    entity.setReg_dt(Optional.ofNullable(rs.getTimestamp("reg_dt")).map(Timestamp::toLocalDateTime).orElse(null));
    entity.setMod_dt(Optional.ofNullable(rs.getTimestamp("mod_dt")).map(Timestamp::toLocalDateTime).orElse(null));
    entity.setMod_id(rs.getString("mod_id"));
    entity.setReg_id(rs.getString("reg_id"));
    return entity;
  }
}
