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

  @Bean
  public DataSource dataSource() {
    log.info("Connecting to database...");

    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(dbDriverClassName);
    hikariConfig.setJdbcUrl(dbURL);
    hikariConfig.setUsername(userName);
    hikariConfig.setPassword(password);

    hikariConfig.setMaximumPoolSize(5);
//    hikariConfig.setConnectionTestQuery("SELECT 1");
//    hikariConfig.setPoolName("springHikariCP");
    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

    return dataSource;
  }

  /**
   * @Transactional 애노테이션 Bean 설정
   * <pre>
   * 속성	              설 명	                                                                                          사용 예
   * isolation	        Transaction의 isolation Level. 별도로 정의하지 않으면 DB의 Isolation Level을 따름.	                  @Transactional(isolation=Isolation.DEFAULT)
   * propagation	      트랜잭션 전파규칙을 정의 , Default=REQURIED	                                                        @Transactional(propagation=Propagation.REQUIRED)
   * readOnly	          해당 Transaction을 읽기 전용 모드로 처리 (Default = false)	                                        @Transactional(readOnly = true)
   * rollbackFor	      정의된 Exception에 대해서는 rollback을 수행	                                                        @Transactional(rollbackFor=Exception.class)
   * noRollbackFor	    정의된 Exception에 대해서는 rollback을 수행하지 않음.	                                                @Transactional(noRollbackFor=Exception.class)
   * timeout	          지정한 시간 내에 해당 메소드 수행이 완료되지 않은 경우 rollback 수행. -1일 경우 no timeout (Default = -1)	@Transactional(timeout=10)
   * </pre>
   * @param dataSource
   * @return DataSourceTransactionManager
   */
  @Bean
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }

}
