package com.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

@SpringBootApplication
public class EpsBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpsBackApplication.class, args);
	}

	@Bean("dropboxClient")
	public DbxClientV2 dropboxClient() throws DbxException {
		String ACCESS_TOKEN = "your_DropBox_Token";
		DbxRequestConfig config = new DbxRequestConfig("dropbox/eps");
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		return client;
	}
}
