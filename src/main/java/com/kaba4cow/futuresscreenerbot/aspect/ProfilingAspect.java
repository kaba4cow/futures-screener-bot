package com.kaba4cow.futuresscreenerbot.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.util.tool.TimeFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConditionalOnProperty(prefix = "application.aspect.profiling", name = "enabled", havingValue = "true")
@Aspect
@Component
public class ProfilingAspect {

	@Around("@annotation(WithProfiling)")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTimeNanos = System.nanoTime();

		String methodString = getMethodString(joinPoint);
		String argsString = getArgsString(joinPoint);

		log.info("Executing method {} with args {}", methodString, argsString);

		Object result = joinPoint.proceed();

		WithProfiling annotation = getAnnotation(joinPoint);
		TimeUnit timeUnit = annotation.timeUnit();

		long elapsedTimeNanos = System.nanoTime() - startTimeNanos;
		String elapsedTimeFormatted = TimeFormatter.format(elapsedTimeNanos, timeUnit);
		log.info("Method {} took {}", methodString, elapsedTimeFormatted);

		return result;
	}

	private WithProfiling getAnnotation(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(WithProfiling.class);
	}

	private String getMethodString(ProceedingJoinPoint joinPoint) {
		return String.format("%s.%s", joinPoint.getSignature().getDeclaringType().getSimpleName(),
				joinPoint.getSignature().getName());
	}

	private String getArgsString(ProceedingJoinPoint joinPoint) {
		return Arrays.toString(joinPoint.getArgs());
	}

}
