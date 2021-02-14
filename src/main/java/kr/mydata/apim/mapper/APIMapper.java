package kr.mydata.apim.mapper;

import kr.mydata.apim.vo.APIEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    entity.setReg_dt(rs.getString("reg_dt"));
    entity.setMod_dt(rs.getString("mod_dt"));
    entity.setMod_id(rs.getString("mod_id"));
    entity.setReg_id(rs.getString("reg_id"));
    return entity;
  }
}
