package com.example.firstproject;

import com.example.firstproject.storage.StorageProperties;
import com.example.firstproject.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EntityScan("com.example.firstproject.entity")
@EnableConfigurationProperties(StorageProperties.class)
public class FirstprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstprojectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
