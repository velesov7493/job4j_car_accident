package ru.job4j.accident.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HbmConfig {

	private static final Logger LOG = LoggerFactory.getLogger(HbmConfig.class);

	@Bean
	public DataSource postgeSqlDs() {
		DataSource result = null;
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			result = (DataSource) envCtx.lookup("jdbc/postgres");
		} catch (NamingException ex) {
			LOG.error(
				"Критическая ошибка инициализации DataSource: "
				+ "ресурс подключения к БД не обнаружен в контексте приложения!", ex
			);
			LOG.info("Выключаюсь...");
			System.exit(2);
		}
		return result;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(
		@Value("${hibernate.dialect}") String dialect,
		DataSource ds
	) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(ds);
		sfb.setPackagesToScan("ru.job4j.accident.models");
		Properties cfg = new Properties();
		cfg.setProperty("hibernate.dialect", dialect);
		sfb.setHibernateProperties(cfg);
		return sfb;
	}

	@Bean
	public PlatformTransactionManager transactionManager(LocalSessionFactoryBean sfb) {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setSessionFactory(sfb.getObject());
		return tm;
	}
}
