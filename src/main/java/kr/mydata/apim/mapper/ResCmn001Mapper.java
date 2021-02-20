package kr.mydata.apim.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn001Sub;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ResCmn001Mapper implements RowMapper<ResCmn001> {

    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public ResCmn001 mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResCmn001 e = new ResCmn001();
        e.setRsp_code(rs.getString("rsp_code"));
        e.setRsp_msg(rs.getString("rsp_msg"));
        e.setVersion(rs.getString("version"));
        e.setMin_version(rs.getString("min_version"));
        e.setApi_cnt(rs.getString("api_cnt"));
        String strApiList = rs.getString("api_list");
        List<ResCmn001Sub> list = mapper.readValue(strApiList, mapper.getTypeFactory().constructCollectionType(ArrayList.class, ResCmn001Sub.class));
        e.setApi_list(list);
        return e;
    }
}
