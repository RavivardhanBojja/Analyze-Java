package com.capgemini.Analyze.config;

import com.capgemini.Analyze.exception.FeatureFlagDisabledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobaLExceptionHandler {
@ExceptionHandler(FeatureFlagDisabledException.class)

    public ResponseEntity<Object> handleFeatureFlagDisabledException(FeatureFlagDisabledException ex) {
        Logger LOG = Logger.getLogger(GlobaLExceptionHandler.class.getName());
        LOG.info("Feature flag is disabled");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Please validate Feature flag is disabled/Enabled or is registered");
        body.put("status", HttpStatus.FAILED_DEPENDENCY);
        body.put("error", "Feature flag is disabled");


        // Return a custom response
        return new ResponseEntity<>(body, HttpStatus.FAILED_DEPENDENCY); // or any other status you see fit
    }
}
