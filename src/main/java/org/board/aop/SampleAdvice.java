package org.board.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Component 스프링의 빈으로 인식되기 위해서 설정
// Aspect aop기능을 하는 클래스의 선언
@Component
@Aspect
public class SampleAdvice {

	private static final Logger logger = 
			LoggerFactory.getLogger(SampleAdvice.class);
	
	// execution 구문 pointcut지정하는 문법
	// MessageService로 시작하는 모든 클래스의 메소드
	@Before("execution(* org.board.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp){
		logger.info("------------------------------");
		logger.info("------------------------------");
		logger.info(Arrays.toString(jp.getArgs()));
	}
	
	// target메소드 실행 이전과 이후 모두 적용
	// execution 실행
	@Around("execution(* org.board.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp)throws Throwable{
		
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName()+ " : " + (endTime - startTime));
		logger.info("===============================================");
		
		return result;
	}
	
	
	
	
	
	
}
