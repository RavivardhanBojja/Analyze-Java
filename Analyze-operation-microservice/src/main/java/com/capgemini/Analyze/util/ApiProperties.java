package com.capgemini.Analyze.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "apis")
public class ApiProperties {

    private List<ApiConfig> swaggerUrl;   

    public List<ApiConfig> getSwaggerUrl() {
        return swaggerUrl;
    }

    public void setSwaggerUrl(List<ApiConfig> swaggerUrl) {
        this.swaggerUrl = swaggerUrl;
    }

    public static class ApiConfig {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}