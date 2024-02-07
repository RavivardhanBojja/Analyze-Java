package com.capgemini.Analyze.config;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Analyze.util.ApiProperties;

@RestController
public class SwaggerUIConfig {

    private final ApiProperties apiProperties;

    public SwaggerUIConfig(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    @GetMapping("/config")
    public List<ApiProperties.ApiConfig> getConfig() {
        return apiProperties.getSwaggerUrl();
    }
}
