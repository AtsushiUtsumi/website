package com.au.jiro.controller.cache;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserCacheAspect {

	@AfterReturning("@annotation(com.au.jiro.controller.cache.UserHoldCache)")
	public void holdCache(JoinPoint jp) throws Throwable {
		for (Object arg : jp.getArgs()) {
			for (Field field : arg.getClass().getDeclaredFields()) {
				if (field.getAnnotation(UserCacheParam.class) == null) {
					continue;
				}
				final String key = arg.getClass().getName() + ":" + field.getName();
				field.setAccessible(true);
				System.out.println("KEY(HOLD) = " + key);
				System.out.println("VAL(HOLD) = " + field.get(arg));
			}
		}
	}

	@Before("@annotation(com.au.jiro.controller.cache.UserRestoreCache)")
	public void restoreCache(JoinPoint jp) throws Throwable {
		for (Object arg : jp.getArgs()) {
			for (Field field : arg.getClass().getDeclaredFields()) {
				if (field.getAnnotation(UserCacheParam.class) == null) {
					continue;
				}
				final String key = arg.getClass().getName() + ":" + field.getName();
				field.setAccessible(true);
				field.set(arg, "YYY");
				System.out.println("KEY(RESTORE) = " + key);
				System.out.println("VAL(RESTORE) = " + field.get(arg));
			}
		}
	}
}
