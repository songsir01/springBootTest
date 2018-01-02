/**
 * Project Name:SpringBootTest
 * File Name:TestController.java
 * Package Name:com.test.controller
 * Date:2017年8月18日下午5:30:55
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.service.UserServiceI;
import com.test.service.api.ApiConfig;
import com.test.util.ExcelPoiUtil;
import com.test.util.HttpClientUtil;

/**
 * ClassName:TestController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月18日 下午5:30:55 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
public class TestController extends BaseController{
	
	@Autowired
	ApiConfig apiConfig;
	
	@ResponseBody
	@RequestMapping("testInterface")
	public String testInterface(HttpServletRequest request,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		json.put("test", "this is test");
		
		return json.toString();
	}
	
	@RequestMapping("testTestInterface")
	public String testTestInterface(HttpServletRequest request,HttpServletResponse response){
		String test_service = apiConfig.getTest_service();
		String result = HttpClientUtil.httpPostRequest(test_service);
		JSONObject json = JSONObject.parseObject(result);
		System.out.println(json.toString());
		return "";
	}
	
	
	@Test
	public void abc(User...users){
		
		System.out.println(users.length);
	}
	
	@RequestMapping(value = "/newTest")
	public void newTest(){
	
		
		boolean set = springBootRedis.set("song", "song");
		System.out.println(set);
		
		String song = springBootRedis.get("song");
		System.out.println(song);
		
		
	}
	

	@SuppressWarnings("unused")
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserServiceI userService;

	@RequestMapping("/userList")
	public String userList(Model model) {

		System.out.println("到这了。。。");

		int i = 3;

		//User user = userMapper.userList(i);
		
		User user = userService.userList(i);

		model.addAttribute("userList", user);

		return "list";
	}
	
	
	@RequestMapping("/next")
	public String next(){
		return "safe";
	}
	/*@RequestMapping("/login")
	public String login(){
		return "sys/index";
	}*/
	
	public static void main(String[] args){
		try {
			ExcelPoiUtil<User> util = new ExcelPoiUtil<>();
			User user = new User();
			user.setUsername("aa");
			File file = new File("e:\\");
			String aString[]={"a","b"};
			List<User> list2 = new ArrayList<>();
			list2.add(user);
			OutputStream fileOutputStream = new FileOutputStream(file);
			//long date = new Date(1111111111111);
			Map<String ,String > hashMap = new HashMap<>();
			String aString2="2011";
			hashMap.put(aString2, aString2);
			util.exportExcel("b", aString, aString, list2, fileOutputStream, hashMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testtest(){
		
		
	}

}
