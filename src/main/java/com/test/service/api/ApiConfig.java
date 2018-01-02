/**
 * Project Name:SpringBootTest
 * File Name:ApiConfig.java
 * Package Name:com.test.service.api
 * Date:2018年1月2日下午3:54:36
 * Copyright (c) 2018, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.service.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName:ApiConfig <br/>
 * Function: 自定义配置文件的类，读取配置文件的接口路径. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月2日 下午3:54:36 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@ConfigurationProperties(prefix = "test",locations = "classpath:api.properties" )
@Component
public class ApiConfig {
	
	private String test_service;

	public String getTest_service() {
		return test_service;
	}

	public void setTest_service(String test_service) {
		this.test_service = test_service;
	}
	
	
	
}

