package com.capgemini.Analyze.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.Analyze.service.AnalyzeService;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @Autowired
    private AnalyzeService analyzeService;

    @GetMapping("/deployment-variable")
    public Map<String, String> getDeploymentVariable() {
        // Retrieve the deployment variable from the service
        String deploymentVariable = analyzeService.getDeploymentVariable();
        
        // Construct a map to represent the JSON response
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("deployment_variable", deploymentVariable);

        return jsonResponse;
    }
}
