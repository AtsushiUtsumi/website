package com.au.jiro.controller.call;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;//.slf4j.Slf4j;

@Aspect
@Component
@Log4j2
public class UserLogAspect {

	/** コントローラーの実行前後にログを出力する */
	@Around("@annotation(com.au.jiro.controller.call.UserLog)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {

		// 開始ログの出力
		log.info("\n==========================================\nSTART LOG:\n" + jp.getArgs()[0].toString());
		for (Object arg : jp.getArgs()) {
			for (Field field : arg.getClass().getDeclaredFields()) {
				if (field.getAnnotation(UserLogParam.class) == null) {
					continue;
				}

				final String key = arg.getClass().getName() + ":" + field.getName();
				log.error("KEY = " + key);
				field.setAccessible(true);
				log.error("VAL = " + field.get(arg));
				field.set(arg, "ZZZ");
			}
		}

		try {
			// メソッドの実行
			Object result = jp.proceed();

			// メソッドの実行
			log.info("END METHOD\n" + jp.getSignature());

			// 実行結果を呼び出し元に返却
			return result;

		} catch (Exception e) {
			// エラーログ出力
			log.error("USER ERROR\n" + jp.getSignature());

			// エラーの再スロー
			throw e;
		}
	}
}
