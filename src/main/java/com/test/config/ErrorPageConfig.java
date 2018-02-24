/**
 * Project Name:SpringBootTest
 * File Name:ErrorPageConfig.java
 * Package Name:com.test.config
 * Date:2018年1月2日上午10:23:47
 * Copyright (c) 2018, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * ClassName:ErrorPageConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年1月2日 上午10:23:47 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class ErrorPageConfig implements EmbeddedServletContainerCustomizer {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

				container.addErrorPages(error401Page, error404Page, error500Page);

			}
		};
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
	}

	
	/**
	 * 这也修改一下
	 */
}
