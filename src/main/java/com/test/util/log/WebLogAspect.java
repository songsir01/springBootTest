/**
 * Project Name:SpringBootTest
 * File Name:WebLogAspect.java
 * Package Name:com.test.util.log
 * Date:2017年12月27日上午11:52:23
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.util.log;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ClassName:WebLogAspect <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月27日 上午11:52:23 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
@Aspect
@Component
public class WebLogAspect {

	private static Logger logger = LogManager.getLogger(WebLogAspect.class.getName());

	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut("execution(* com.test.*..*Controller.*(..)) || execution(* com.test.*..*API.*(..)) || execution(* com.test.*..*Api.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		logger.info("############################################################");
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String userAgent = request.getHeader("User_Agent");
		Cookie[] cookies = request.getCookies();
		String cookieInfo = getCookieInfo(cookies);

		logger.info("User_Agent:" + userAgent);
		logger.info("Cookie : " + cookieInfo);
		logger.info("URL : " + request.getRequestURL().toString() + " Http_Method : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		// 获取所有参数方法一：
		Enumeration<String> enu = request.getParameterNames();
		if (enu.hasMoreElements()) {
			StringBuffer params = new StringBuffer();
			while (enu.hasMoreElements()) {
				String paraName = enu.nextElement();
				params.append(paraName + ": " + request.getParameter(paraName) + " ");
			}
			logger.info("Params : " + params.toString());
		}
	}

	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
		logger.info("############################################################");

	}

	private String getCookieInfo(Cookie[] cookies) {
		if (cookies != null) {
			StringBuffer sbCookie = new StringBuffer();
			for (Cookie cookie : cookies) {
				sbCookie.append(cookie.getName() + ":" + cookie.getValue());
			}
			return sbCookie.toString();
		}
		return "";
	}
}
