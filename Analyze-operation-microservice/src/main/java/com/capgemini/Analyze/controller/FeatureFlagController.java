package com.capgemini.Analyze.controller;

import com.capgemini.Analyze.dto.FeatureFlagDTO;
import com.capgemini.Analyze.dto.ResponseDTO;
import com.capgemini.Analyze.service.FeatureFlagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/feature-flags")
@Api(tags = "Feature Flags")
public class FeatureFlagController {

    private final FeatureFlagService featureFlagService;

    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    
    @ApiOperation(value = "Get all feature flags with their status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Feature flags retrieved successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error, an unexpected error occurred while processing the request")
    })
    @GetMapping
    public ResponseEntity<?> getAllFeatureFlags() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {


            List<FeatureFlagDTO> flags = featureFlagService.getAllFeatureFlags();
            responseDTO.setResponse(flags);
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setMessage("Internal Server Error, an unexpected error occurred while processing the request");
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @ApiIgnore
    @ApiOperation(value = "Get a feature flag by name")
    @GetMapping("/{featureName}")
    public ResponseEntity<?> getFeatureFlag(@PathVariable String featureName) {
        FeatureFlagDTO flag = featureFlagService.getFeatureFlag(featureName);
        return flag != null ? ResponseEntity.ok(flag) : ResponseEntity.notFound().build();
    }

 
    @ApiOperation(value = "Create or update a feature flag")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Feature flag created/updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request, the feature flag data provided is incorrect"),
            @ApiResponse(code = 500, message = "Internal Server Error, an unexpected error occurred while processing the request")
    })
    @PostMapping
    public ResponseEntity<?> createOrUpdateFeatureFlag(@RequestBody FeatureFlagDTO featureFlagDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {


            FeatureFlagDTO savedFlag = featureFlagService.createOrUpdateFeatureFlag(featureFlagDTO);
            responseDTO.setResponse(savedFlag);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage("Invalid request, the feature flag data provided is incorrect");
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }
    
    @ApiOperation(value = "Update a feature flag status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Feature flag updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request, feature flag name not found or no change in status"),
            @ApiResponse(code = 401, message = "Unauthorized, valid user authentication is required"),
            @ApiResponse(code = 403, message = "Forbidden, the user does not have permissions to update a feature flag"),
            @ApiResponse(code = 500, message = "Internal Server Error, an unexpected error occurred while processing the request"),
            @ApiResponse(code = 502, message = "Bad Gateway, the server received an invalid response from the upstream server")
    })
    @PutMapping("/{featureName}")
    public ResponseEntity<?> updateFeatureFlagStatus(
            @PathVariable String featureName,
            @RequestParam boolean isEnabled,
            @RequestParam String updatedBy) {

        ResponseDTO responseDTO = new ResponseDTO();
        FeatureFlagDTO featureFlagDTO = featureFlagService.getFeatureFlag(featureName);
        if (featureFlagDTO != null && featureFlagDTO.isEnabled() != isEnabled) {
            if (isEnabled) {
                featureFlagService.enableFeature(featureName, updatedBy);
            } else {
                featureFlagService.disableFeature(featureName, updatedBy);
            }
            responseDTO.setResponse(featureFlagDTO);
            responseDTO.setMessage("Feature flag updated successfully");
            return ResponseEntity.ok(responseDTO);

        }
        responseDTO.setMessage("Invalid request, feature flag name not found or no change in status");
        responseDTO.setResponse(ResponseEntity.badRequest().build());

        return ResponseEntity.badRequest().body(responseDTO);
    }
    
    @ApiOperation(value = "Delete a feature flag")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Feature flag deleted successfully"),
            @ApiResponse(code = 400, message = "Invalid request, feature flag name not found"),
            @ApiResponse(code = 401, message = "Unauthorized, valid user authentication is required"),
            @ApiResponse(code = 403, message = "Forbidden, the user does not have permissions to delete a feature flag"),
            @ApiResponse(code = 500, message = "Internal Server Error, an unexpected error occurred while processing the request")
    })
    @DeleteMapping("/{featureName}")
    public ResponseEntity<?> deleteFeatureFlag(@PathVariable String featureName) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            featureFlagService.deleteFeatureFlag(featureName);
            responseDTO.setResponse("Feature flag deleted successfully");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage("Invalid request, feature flag name not found");
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
