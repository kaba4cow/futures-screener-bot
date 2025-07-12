package com.kaba4cow.futuresscreenerbot.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ProfilingAspect {

	@Around("@annotation(WithProfiling)")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTimeMillis = System.currentTimeMillis();

		String methodString = getMethodString(joinPoint);
		String argsString = getArgsString(joinPoint);

		log.info("Executing method {} with args {}", methodString, argsString);

		Object result = joinPoint.proceed();

		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		log.info("Method {} took {} ms", methodString, elapsedTimeMillis);

		return result;
	}

	private String getMethodString(ProceedingJoinPoint joinPoint) {
		return String.format("%s.%s", joinPoint.getSignature().getDeclaringType().getSimpleName(),
				joinPoint.getSignature().getName());
	}

	private String getArgsString(ProceedingJoinPoint joinPoint) {
		return Arrays.toString(joinPoint.getArgs());
	}

}
