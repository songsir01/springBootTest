/**
 * Project Name:SpringBootTest
 * File Name:BaseController.java
 * Package Name:com.test.controller
 * Date:2017年12月28日下午5:40:07
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.service.ListManagerServiceI;
import com.test.service.PcenterServiceI;
import com.test.service.RoleServiceI;
import com.test.service.UserServiceI;
import com.test.util.SpringBootRedis;

/**
 * ClassName:BaseController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月28日 下午5:40:07 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class BaseController {
	
	@Autowired
	public SpringBootRedis<String> springBootRedis;
	@Autowired
	public UserServiceI userService;
	@Autowired
	public RoleServiceI rolerService;
	@Autowired
	public PcenterServiceI pcenterService;
	@Autowired
	public ListManagerServiceI listService;
	
	
}

