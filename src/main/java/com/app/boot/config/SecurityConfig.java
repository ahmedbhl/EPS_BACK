package com.app.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.app.boot.repository.UserReposiroty;

@SuppressWarnings("deprecation")
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
		http.csrf().disable().exceptionHandling().and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.antMatcher("/api/v1/**").authorizeRequests()
				// @formatter:off
				.antMatchers("/api/v1/users/currentUser").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/users/professor").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/users/student").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/users/administration").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v1/users/activate/**").permitAll()
				.antMatchers("/api/v1/users/**").hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR)
				.antMatchers("/api/v1/establishments/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/levels/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/fields/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/classes/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/courses/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/groups/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).antMatchers("/api/v1/posts/**")
				.hasAnyAuthority(SUPER_ADMIN, ADMINISTRATION, STUDENT, PROFESSOR).anyRequest().authenticated().and()
				.httpBasic();
		http.cors();

	}

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
						.allowedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "authorization")
						.exposedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "authorization")
						.maxAge(4200);
			}
		};
	}

}
