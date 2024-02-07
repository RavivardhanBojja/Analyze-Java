package com.capgemini.Analyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.capgemini.Analyze.repository")
@EnableTransactionManagement
public class AnalyzeOperationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzeOperationMicroserviceApplication.class, args);
	}

	
}

