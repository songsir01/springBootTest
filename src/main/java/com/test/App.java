package com.test;
/**
 * Project Name:SpringBootTest
 * File Name:App.java
 * Package Name:com.test
 * Date:2017年8月18日下午5:23:39
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName:App <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月18日 下午5:23:39 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
@SuppressWarnings("deprecation")
@MapperScan("com.test.mapper")
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan //过滤器
@EnableTransactionManagement // 事务
public class App  extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("SpringBoot启动。。。");
	}

}

/**
 * 这里 修改修改改一下
 * 
 * zaizai xiugai u gaiuxii gaioug
 */
