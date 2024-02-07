package com.capgemini.Analyze.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.capgemini.Analyze.exception.QEPException;

@Aspect
@Component
public class AspectLogging {

 private static final Logger log = LogManager.getLogger(AspectLogging.class);

 @Around("execution(* com.capgemini.Analyze.controller.*.*(..)) || execution(* com.capgemini.Analyze.serviceimpl.*.*(..))")
 public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
     StopWatch stopWatch = new StopWatch();
     stopWatch.start();

     Object retVal = null;
     try {
         retVal = joinPoint.proceed();
     } catch (QEPException e) {
         log.error("Exception occurred: " + e.getMessage(), e);
         throw new QEPException(e.getMessage());
     }

     stopWatch.stop();

     StringBuilder logMessage = new StringBuilder();
     logMessage.append("Executing: ");
     logMessage.append(joinPoint.getTarget().getClass().getName());
     logMessage.append(".");
     logMessage.append(joinPoint.getSignature().getName());
     logMessage.append("()");
     logMessage.append(" Execution time: ");
     logMessage.append(stopWatch.getTotalTimeMillis());
     logMessage.append(" ms");

     log.info(logMessage.toString());

     return retVal;
 }
}
