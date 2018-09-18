package spittr.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataConfig {

  @Bean
  public DataSource dataSource() {
	  BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://127.0.0.1:3306/lpcspring?useUnicode=true&characterEncoding=UTF-8&ssl=true&serverTimezone=GMT");
	    ds.setUsername("root");
	    ds.setPassword("password");
	    return ds;

  }
  
  @Bean
  public JdbcOperations jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
