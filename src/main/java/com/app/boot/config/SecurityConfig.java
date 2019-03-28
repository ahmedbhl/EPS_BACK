package com.app.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.boot.repository.UserReposiroty;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserReposiroty.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	/**
	 * Role of the super admin
	 */
	private static final String SUPER_ADMIN = "SUPER_ADMIN";

	/**
	 * Role of the administration
	 */
	private static final String ADMINISTRATION = "ADMINISTRATION";

	/**
	 * Role of the Student
	 */
	private static final String STUDENT = "STUDENT";

	/**
	 * Role of the Professor
	 */
	private static final String PROFESSOR = "PROFESSOR";

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.csrf().disable();
		http.authorizeRequests()
				// @formatter:off
				.antMatchers("/api/v1/users/**").hasAnyRole(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR)
				.antMatchers("/api/v1/establishments.*").hasAnyRole(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR)
				.antMatchers("/api/v1/levels").hasAnyRole(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR)
				.anyRequest().fullyAuthenticated().and().formLogin().permitAll();
		// .httpBasic();

	}

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

}
