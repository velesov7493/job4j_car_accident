package ru.job4j.accident.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource ds;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        ds = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/login", "/register", "/scripts/**", "/styles/**", "/fonts/**"
                ).permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String usersSQL =
                "SELECT email AS username, pass AS password, enabled "
                        + "FROM tz_users WHERE email = ?";
        String rolesSQL =
                "SELECT u.email AS username, a.authority "
                        + "FROM tz_roles AS a "
                        + "INNER JOIN tz_users AS u ON a.id=u.id_role AND u.email=?";
        auth.jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(usersSQL)
                .authoritiesByUsernameQuery(rolesSQL);
    }

    @Bean
    @Scope("prototype")
    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}