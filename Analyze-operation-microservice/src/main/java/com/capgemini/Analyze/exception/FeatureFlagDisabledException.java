package com.capgemini.Analyze.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeatureFlagDisabledException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(FeatureFlagDisabledException.class);

    public FeatureFlagDisabledException(String message) {
        super(message);
        logger.error("Feature Flag Disabled: " + message); 
    }

    public FeatureFlagDisabledException(String message, Throwable cause) {
        super(message, cause); 
        logger.error("Feature Flag Disabled: " + message, cause);
    }
}
