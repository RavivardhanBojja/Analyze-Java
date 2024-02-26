package com.capgemini.Analyze.serviceImpl;

import org.springframework.stereotype.Service;
import com.capgemini.Analyze.service.AnalyzeService;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    private String deploymentVariable;

    public void setDeploymentVariable(String deploymentVariable) {
        this.deploymentVariable = deploymentVariable;
    }

    public String getDeploymentVariable() {
        return deploymentVariable;
    }
}
