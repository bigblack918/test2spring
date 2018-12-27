package com.leo.testspring.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Pointcut(value="execution(* com.leo.testspring.controller.BookController.*(..))")
    public void aspectService() {
    }

    @Before(value="aspectService()")
    public void before(){
        System.out.println("Before");
    }

    @After(value="aspectService()")
    public void after(){
        System.out.println("After");
    }
}
