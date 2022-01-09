package ru.job4j.accident.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JdbcConfig {

	@Bean
	public DataSource postgeSqlDs() throws NamingException {
		InitialContext initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		return (DataSource) envCtx.lookup("jdbc/postgres");
	}

	@Bean
	public JdbcTemplate jdbc(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
