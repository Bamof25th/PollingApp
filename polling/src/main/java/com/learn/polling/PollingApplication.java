package com.learn.polling;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PollingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollingApplication.class, args);
	}

	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
