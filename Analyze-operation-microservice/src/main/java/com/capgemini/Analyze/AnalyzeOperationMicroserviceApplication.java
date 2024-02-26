package com.capgemini.Analyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.capgemini.Analyze.serviceImpl.AnalyzeServiceImpl;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.capgemini.Analyze.repository")
@EnableTransactionManagement
@EnableAutoConfiguration
public class AnalyzeOperationMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyzeOperationMicroserviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDeploymentVariable(AnalyzeServiceImpl analyzeService) {
        return args -> {
            String deploymentVariable = extractDeploymentVariable(args);
            analyzeService.setDeploymentVariable(deploymentVariable);
            if (deploymentVariable != null) {
                System.out.println("Deployment variable set successfully: " + deploymentVariable);
            } else {
                System.out.println("Deployment variable is null or invalid.");
            }
        };
    }

    private String extractDeploymentVariable(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-Ddeployment_variable".equals(args[i]) && i < args.length - 1) {
                String providedVariable = args[i + 1];
                // Check if the provided variable matches one of the predefined variables
                if (providedVariable.equals("AITCG") ||
                    providedVariable.equals("qeinsights-analyze") ||
                    providedVariable.equals("QEP2.o")) {
                    return providedVariable;
                } else {
                    // If the provided variable does not match, print a warning message
                    System.out.println("Provided deployment variable does not match any predefined variable.");
                }
            }
        }
        return null;
    }
}
