package kr.mydata.apim.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String dbDriverClassName;

    @Value("${spring.datasource.url}")
    private String dbURL;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Bean
    public DataSource dataSource() {
        log.info("Connecting to database...");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dbDriverClassName);
        hikariConfig.setJdbcUrl(dbURL);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
