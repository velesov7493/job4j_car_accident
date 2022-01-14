package ru.job4j.accident.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final DataSource ds;

	@Autowired
	public SecurityConfig(PasswordEncoder passEncoder, DataSource dataSource) {
		passwordEncoder = passEncoder;
		ds = dataSource;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login", "/scripts/**", "/styles/**", "/fonts/**")
			.permitAll()
			.antMatchers("/**")
			.hasAnyRole("ADMIN", "USER")
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)
			.permitAll()
			.and()
			.csrf()
			.disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(ds)
			.passwordEncoder(passwordEncoder)
			.withUser("user")
			.password(passwordEncoder.encode("123456")).roles("USER");
	}
}
