/**
 * Project Name:SpringBootTest
 * File Name:RoleDaoI.java
 * Package Name:com.test.dao
 * Date:2017年8月30日下午3:58:50
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.pojo.Role;

/**
 * ClassName:RoleDaoI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月30日 下午3:58:50 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface RoleMapper {

	@SuppressWarnings("rawtypes")
	List getRoleList();

	@SuppressWarnings("rawtypes")
	List getRoleById(Role role);

	void delBefRp(Role role);

	int insRpTable(@Param("pid")int pid, @Param("rid")int rid);

	int roleDel(int rid);

	Role getRoleByName(Role role);

	int addRoleAndGetRid(Role role);

}

