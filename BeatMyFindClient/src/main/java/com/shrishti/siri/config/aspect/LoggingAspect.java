package com.shrishti.siri.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {

//	@Before("execution(public * *(..))")
	@Before("execution (* com.shrishti.siri..*.*(..))")
	public void logEntry(JoinPoint joinPoint){
		System.out.println("ENTRY>>>>>>> "+joinPoint.getSignature().getName()+" method of "+joinPoint.getTarget().getClass().getName()+" class");
	}
	
//	@After("execution(public * *(..))")
	@After("within(com.shrishti.siri..*)")
	public void logExit(JoinPoint joinPoint){
		System.out.println("EXIT>>>>>>> "+joinPoint.getSignature().getName()+" method of "+joinPoint.getSignature().getDeclaringTypeName()+" class");
	}
}
