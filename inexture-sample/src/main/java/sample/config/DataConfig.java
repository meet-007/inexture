package sample.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DataConfig {
	@Autowired
	Environment env;

	@Bean
	BasicDataSource getDataSource() {
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/inexture_demo_spring");
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("root");
		return basicDataSource;
	}

	@Bean
	LocalSessionFactoryBean getSessionFactory() {
		final LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("sample.models");
		lsfb.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return lsfb;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
