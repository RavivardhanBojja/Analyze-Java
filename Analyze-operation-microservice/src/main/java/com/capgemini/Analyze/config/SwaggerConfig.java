package com.capgemini.Analyze.config;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.capgemini.Analyze.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(
                    new springfox.documentation.service.Tag("Feature Flags","Feature Flags API for Analyze controllers and endpoints.")
                    );
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Analyze Java API Docs: ",
            "Swagger documentation for the Analyze Java API endpoints controllers and models",
            "1.0",
            "Terms of service URL",
            new Contact("Analyze", "", "email@domain.com"),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0.html",
            Collections.emptyList()
        );
    }
}
