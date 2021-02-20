package kr.mydata.apim.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

@Slf4j
@Service
public class APIServiceImpl implements APIService {

    final DataSource dataSource;
    final JdbcTemplate jdbcTemplate;

    public APIServiceImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertJSONBObject() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            String URL = connection.getMetaData().getURL();
            Statement statement = connection.createStatement();
            String sql = "SELECT now()";
            ResultSet resultSet = statement.executeQuery(sql);


            log.debug("connection : {}", connection);
            log.debug("URL : {}", URL);
            log.debug("Result : {}", resultSet);

            String result = jdbcTemplate.queryForObject(sql, String.class);
            log.debug("template result : {}", result);
            log.debug("template resultSet : {}", resultSet);
        }

        return 0;
    }

    @Override
    public LocalDateTime getDate() throws Exception {
        String sql = "SELECT now()";
        LocalDateTime result = jdbcTemplate.queryForObject(sql, LocalDateTime.class);

        return result;
    }
}
