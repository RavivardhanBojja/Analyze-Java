package com.capgemini.Analyze.aspect;


import com.capgemini.Analyze.annotations.FeatureFlagAnnotation;
import com.capgemini.Analyze.dto.FeatureFlagDTO;
import com.capgemini.Analyze.exception.FeatureFlagDisabledException;
import com.capgemini.Analyze.service.FeatureFlagService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.logging.Logger;


@Aspect
@Component
public class FeatureFlagAspect {
    private final FeatureFlagService featureFlagService;
    Logger LOG = Logger.getLogger(FeatureFlagAspect.class.getName());

    @Autowired
    public FeatureFlagAspect(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @Around("@annotation(featureFlagAnnotation)")
    public Object checkFeatureFlag(ProceedingJoinPoint joinPoint, FeatureFlagAnnotation featureFlagAnnotation) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        FeatureFlagAnnotation annotation = method.getAnnotation(FeatureFlagAnnotation.class);
        String featureName = annotation.featureName();

        FeatureFlagDTO flag = featureFlagService.getFeatureFlag(featureName);

        if (flag != null && flag.isEnabled()) {
            LOG.info("Feature flag is enabled: " + featureName);
            return joinPoint.proceed(); // Proceed if flag is enabled
        } else {
            LOG.info("Feature flag is disabled: " + featureName);
            throw new FeatureFlagDisabledException("Feature flag is disabled: " + featureName);
        }
    }
}