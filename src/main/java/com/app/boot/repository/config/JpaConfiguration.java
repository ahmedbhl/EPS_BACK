package com.app.boot.repository.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Jpa configuration
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.app.boot.model" })
@EnableJpaRepositories(basePackages = { "com.app.boot.repository" }, repositoryImplementationPostfix = "Impl")
@ComponentScan(basePackages = { "com.app.boot.service" })
@EnableTransactionManagement
public class JpaConfiguration {

}
