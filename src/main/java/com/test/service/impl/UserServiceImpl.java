/**
 * Project Name:SpringBootTest
 * File Name:UserService.java
 * Package Name:com.test.service
 * Date:2017年8月22日上午9:10:11
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.TDaoI;
import com.test.mapper.UserMapper;
import com.test.pojo.Role;
import com.test.pojo.User;
import com.test.service.UserServiceI;
import com.test.util.Md5Util;
import com.test.util.vo.UserVo;

/**
 * ClassName:UserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月22日 上午9:10:11 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Service
public class UserServiceImpl implements UserServiceI {
	
	@Autowired
	private UserMapper userDao;
	@Autowired
	TDaoI tDao;

	@Override
	public User userList(int i) {
		
		// TODO Auto-generated method stub
		return userDao.userList(i);
	}
	/**
	 * 
	 * TODO 注册时验证用户名是否存在.
	 * @see com.test.service.UserServiceI#getuniqueList(com.test.pojo.User)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getuniqueList(User user) {
		
		// TODO Auto-generated method stub
		return userDao.getuniqueList(user);
	}
	/**
	 * 
	 * TODO 注册用户.
	 * @see com.test.service.UserServiceI#userAdd(com.test.pojo.User)
	 */
	@Override
	public int userAddAndGetId(User user) {
		
		// TODO Auto-generated method stub
		Md5Util md5Util = new Md5Util();
		
		@SuppressWarnings("static-access")
		String md5Password = md5Util.MD5(user.getPassword()); //密码md5加密
		
		user.setPassword(md5Password);
		
		return userDao.userAddAndGetId(user);
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getQuestionList() {
		
		// TODO Auto-generated method stub
		return userDao.getQuestionList();
	}
	/**
	 * 
	 * TODO 登录验证.
	 * @see com.test.service.UserServiceI#userLogin(com.test.pojo.User)
	 */
	@Override
	public User userLogin(User user) {
		
		// TODO Auto-generated method stub
		String password = user.getPassword();
		Md5Util md5Util = new Md5Util();
		@SuppressWarnings("static-access")
		String md5Password = md5Util.MD5(password);
		user.setPassword(md5Password);
		return userDao.userLogin(user);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getRoleByRid(Role role) {
		
		// TODO Auto-generated method stub
		return userDao.getRoleByRid(role);
	}
	@Override
	public int getUserCount(UserVo userVo) {
		return userDao.getUserCount(userVo);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserList(UserVo userVo) {
		
		// TODO Auto-generated method stub
		return userDao.getUserList(userVo);
	}
	@Override
	public int userDel(int uid) {
		
		// TODO Auto-generated method stub
		return userDao.userDel(uid);
	}
	@Override
	public User userByUid(User user) {
		
		// TODO Auto-generated method stub
		return userDao.userByUid(user);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getRoleList() {
		
		// TODO Auto-generated method stub
		return userDao.getRoleList();
	}
	@Override
	public int userUpdate(User user) {
		
		// TODO Auto-generated method stub
		
		return userDao.userUpdate(user);
	}
	@Override
	public int userPassUpdate(User user) {
		
		Md5Util md5Util = new Md5Util();
		
		@SuppressWarnings("static-access")
		String md5Pasword = md5Util.MD5(user.getPassword());
		
		user.setPassword(md5Pasword);
		
		return userDao.userPassUpdate(user);
	}
	

}

