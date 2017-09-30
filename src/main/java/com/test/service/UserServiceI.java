/**
 * Project Name:SpringBootTest
 * File Name:UserServiceI.java
 * Package Name:com.test.service
 * Date:2017年8月22日上午9:09:42
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.service;

import java.util.List;

import com.test.pojo.Role;
import com.test.pojo.User;
import com.test.util.vo.UserVo;

/**
 * ClassName:UserServiceI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月22日 上午9:09:42 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
public interface UserServiceI {

	User userList(int i);

	@SuppressWarnings("rawtypes")
	List getuniqueList(User user);

	int userAddAndGetId(User user);

	@SuppressWarnings("rawtypes")
	List getQuestionList();

	User userLogin(User user);

	@SuppressWarnings("rawtypes")
	List getRoleByRid(Role role);

	int getUserCount(UserVo userVo);

	@SuppressWarnings("rawtypes")
	List getUserList(UserVo userVo);

	int userDel(int parseInt);

	User userByUid(User user);

	@SuppressWarnings("rawtypes")
	List getRoleList();

	int userUpdate(User user);

	int userPassUpdate(User user);

}
