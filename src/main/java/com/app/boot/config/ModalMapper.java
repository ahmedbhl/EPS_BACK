package com.app.boot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModalMapper {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
