package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

//import java.lang.classfile.MethodSignature;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution (* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint
    ) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {
            // log the exception
            System.out.println(exc.getMessage());

            throw exc;
        }


        // get end timestamp
        long end = System.currentTimeMillis();

        // compute the duration and display it
        long duration = end - begin;

        System.out.println("\n=====>>> Duration " + duration/1000.0 + " seconds");

        return result;
    }

    @After("execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method " + method);
    }

    @AfterThrowing(
            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint,
            Throwable theExc
    ) {

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method " + method);
      // log the exception
        System.out.println("\n======> The exception is: " + theExc);
    }

    @AfterReturning(
            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method " + method);

        // print out the results of the methods call
        System.out.println("\n=====>>> before modification result is " + result);

        // lets post process the data and modify it :)

        // convert the account name to uppercase
        convertAccountNamestoUpperCase(result);

        System.out.println("\n=====>>> after modification result is " + result);
    }

    private void convertAccountNamestoUpperCase(List<Account> result) {

        // loop through the account

        for (Account tempAccount: result) {
            // get uppercase version of the name
            String theUpperCase = tempAccount.getName().toUpperCase();
            //update the name on the account
            tempAccount.setName(theUpperCase);
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccoundAdvice(JoinPoint theJointPoint) {

        System.out.println("\n ==========>>>> Executing @Before advice on method");

        // display the method signature
//        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();

        // display method arguments
        Object[] args = theJointPoint.getArgs();

        for(Object tempArg : args) {
            System.out.println("tempArg " + tempArg);

            if (tempArg instanceof Account) {
                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name " + theAccount.getName());
                System.out.println("account level " + theAccount.getLevel());
            }
        }
    }

}