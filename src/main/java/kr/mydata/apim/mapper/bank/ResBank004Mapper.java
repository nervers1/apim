package kr.mydata.apim.mapper.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mydata.apim.vo.bank.ResBank004;
import kr.mydata.apim.vo.bank.ResBank004Sub;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResBank004Mapper implements RowMapper<ResBank004> {
	@Override
	public ResBank004 mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResBank004 entity = new ResBank004();
		log.info("rs --> {}", rs);
		try {
			entity.setRsp_code(rs.getString("rsp_code"));
			entity.setRsp_msg(rs.getString("rsp_msg"));
			entity.setNext_page(rs.getString("next_page"));
			entity.setTrans_cnt(rs.getString("trans_cnt"));
			String trans = rs.getString("trans_list");
			ObjectMapper mapper = new ObjectMapper();
			List<ResBank004Sub> trans_list = mapper.readValue(trans,
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, ResBank004Sub.class));
			log.info("list --> {}", trans_list);
			entity.setTrans_list(trans_list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
