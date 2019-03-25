package com.app.boot.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 2 configuration
 * 
 * @author Zayneb
 *
 */
@Configuration
@EnableSwagger2
@Import(value = { BeanValidatorPluginsConfiguration.class })
@PropertySource(value = { "classpath:/swagger/swagger-messages_fr.properties" })
public class SwaggerConfig {

	/**
	 * Create a Docket bean to configure Swagger 2 for the application
	 * 
	 * @param properties
	 *            Api info properties
	 * @return a Docket bean to configure Swagger 2 for the application
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("private-api").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.app.boot.controller")).paths(regex("/api/v1/users.*")).paths(regex("/api/v1/establishments.*"))
				.build();

	}
	


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("EPS Cedro Rest API")
				.description("EPS Cedro tech Ui for comunicate with all the API of the application")
				.termsOfServiceUrl("https://www.cedrotech.com/").license("Cedro License")
				.licenseUrl("comercial@cedrotech.com").version("1.0").build();
	}
}
