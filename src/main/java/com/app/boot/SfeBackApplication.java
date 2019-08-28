package com.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

@SpringBootApplication
public class SfeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfeBackApplication.class, args);
	}

	@Bean("dropboxClient")
	public DbxClientV2 dropboxClient() throws DbxException {
		String ACCESS_TOKEN = "ZJuPTOKaC7AAAAAAAAAADHeALcsUbYp4ne47ZdDiblhMqAMC8eZHoOz_6KBjCmOk";
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		return client;
	}
}
