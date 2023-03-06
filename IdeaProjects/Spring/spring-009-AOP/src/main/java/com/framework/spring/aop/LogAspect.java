package com.framework.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.framework.spring.service..*())")
    public void generic() {

    }

    @Before("generic()")
    public void beforeAdvice() {
        System.out.println("before");
    }

    @AfterReturning("generic()")
    public void afterReturningAdvice() {
        System.out.println("afterReturning");
    }


    @Around("generic()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        joinPoint.proceed();
        System.out.println("around after");
    }

}
