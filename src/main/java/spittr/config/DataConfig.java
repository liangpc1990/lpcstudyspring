package spittr.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataConfig {

	@Bean(name = "dataSource")
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl(
				"jdbc:mysql://127.0.0.1:3306/lpctest?useUnicode=true&characterEncoding=UTF-8&ssl=true&serverTimezone=GMT");
		ds.setUser("root");
		ds.setPassword("123456");
		/*
		 * ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); ds.setUrl(
		 * "jdbc:mysql://127.0.0.1:3306/lpcspring?useUnicode=true&characterEncoding=UTF-8&ssl=true&serverTimezone=GMT"
		 * ); ds.setUsername("root"); ds.setPassword("123456");
		 */
		return ds;

	}

	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "mybatisMasterSqlSessionFactory")
	public SqlSessionFactoryBean mybatisMasterSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		try {
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:event/mapper/*.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 设置MyBatis分页插件
		PageInterceptor pageInterceptor = this.initPageInterceptor();
		bean.setPlugins(new Interceptor[] { pageInterceptor });
		return bean;
	}

	public PageInterceptor initPageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mScannerConfigurer = new MapperScannerConfigurer();
		mScannerConfigurer.setSqlSessionFactoryBeanName("mybatisMasterSqlSessionFactory");
		mScannerConfigurer.setBasePackage("event");
		return mScannerConfigurer;
	}

	@Bean(name = "mybatisMasterTransactionManager")
	public DataSourceTransactionManager mybatisMasterTransactionManager(
			@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "mybatisMasterSqlSessionTemplate")
	public SqlSessionTemplate mybatisMasterSqlSessionTemplate(
			@Qualifier("mybatisMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
