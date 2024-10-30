package com.luv2code.springboot.thymeleafdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations for controller
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    // do the same for service and dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forDAOPackage() || forServicePackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // display the method we are calling
        String method = theJoinPoint.getSignature().toShortString();
        logger.info("=====> in @Before: calling method: " + method);

        //get the arguments
        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg: args) {
            logger.info("=====> argument:  " + tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning= "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        // display the method we are calling
        String method = theJoinPoint.getSignature().toShortString();
        logger.info("=====> in @After returning: calling method: " + method);

        //display data returned
        logger.info("=====> result: " + theResult);

    }

}
