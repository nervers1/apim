package kr.mydata.apim.mapper.card;

import kr.mydata.apim.vo.card.ResCard001;
import kr.mydata.apim.vo.card.ResCard001Sub;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResCard001Mapper implements RowMapper<ResCard001> {
  @SneakyThrows
  @Override
  public ResCard001 mapRow(ResultSet rs, int rowNum) throws SQLException {
    List<ResCard001Sub> card_list = null;
    ResCard001 entity = new ResCard001();
    entity.setRsp_code(rs.getString("rsp_code"));
    entity.setRsp_msg(rs.getString("rsp_msg"));
    entity.setSearch_timestamp(rs.getString("search_timestamp"));
    entity.setCard_cnt(rs.getInt("card_cnt"));
    int list_size = rs.getInt("card_cnt");
    for (int i = 0; i < list_size; i++) {
      ResCard001Sub sub = rs.getObject("card_list", ResCard001Sub.class);
      card_list.add(sub);
    }
    entity.setCard_list(card_list);
    return entity;
  }
}
