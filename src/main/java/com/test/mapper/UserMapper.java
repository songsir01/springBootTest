/**
 * Project Name:SpringBootTest
 * File Name:UserDaoI.java
 * Package Name:com.test.dao
 * Date:2017年8月22日上午9:10:51
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.pojo.Role;
import com.test.pojo.User;
import com.test.util.vo.UserVo;

/**
 * ClassName:UserDaoI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月22日 上午9:10:51 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
public interface UserMapper {
	//@Select("select * from user where uid = #{i}")
	User userList(@Param("i") int i);

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

	int userDel(int uid);

	User userByUid(User user);

	@SuppressWarnings("rawtypes")
	List getRoleList();

	int userUpdate(User user);

	int userPassUpdate(User user);

}
