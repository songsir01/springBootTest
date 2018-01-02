/**
 * Project Name:SpringBootTest
 * File Name:RoleServiceI.java
 * Package Name:com.test.service
 * Date:2017年8月30日下午3:57:41
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.service;

import java.util.List;

import com.test.pojo.Role;

/**
 * ClassName:RoleServiceI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月30日 下午3:57:41 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface RoleServiceI {

	@SuppressWarnings("rawtypes")
	List getRoleList();

	@SuppressWarnings("rawtypes")
	List getRoleById(Role role);

	void delBefRp(Role role);

	int insRpTable(String pid, int rid);

	int roleDel(int rid);

	Role getRoleByName(Role role);

	int addRoleAndGetRid(Role role);

}

