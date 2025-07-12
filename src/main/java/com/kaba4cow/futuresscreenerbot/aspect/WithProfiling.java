package com.kaba4cow.futuresscreenerbot.aspect;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface WithProfiling {

	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
