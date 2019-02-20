package com.app.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.boot.repository.UserReposiroty;
import com.app.boot.service.CustomUserService;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserReposiroty.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserService userDetailsService;

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
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.csrf().disable()
		.antMatcher("/api/v1/**")
		.authorizeRequests()
		.antMatchers("/api/v1/user/**").hasAnyRole(SUPER_ADMIN)
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin().permitAll();

	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
