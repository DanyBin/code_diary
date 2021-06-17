package com.spring5.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoggerAspect
 * @Author bin
 * @Date 2021/4/1 下午12:53
 * @Decr TODO
 *  //切面类
 * @Link TODO
 **/
@Aspect
@Component
public class LoggerAspect {

    /**
     * @Before：该注解用来定义前置通知，通知方法会在目标方法调用之前执行
     * execution：在方法执行时触发
     * @param joinPoint
     */
    @Before("execution(* Cal.add(..))")
    public void before(JoinPoint joinPoint) {
        //获取方法参数
        Object[] args = joinPoint.getArgs();
    }

    @After("execution(* Cal.add(..))")
    public void after(JoinPoint joinPoint){
        // Object[] args = joinPoint.getArgs();

    }

    @Around("execution(* Cal.add(..))")
    public List<Object> around(ProceedingJoinPoint joinPoint) throws Throwable {
        //执行原本的方法
        Object proceed = joinPoint.proceed();
        //对原来方法执行过的，做一下属性的补齐
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(objects);
        return objects;
    }
}
