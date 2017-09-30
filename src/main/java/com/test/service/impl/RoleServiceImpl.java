/**
 * Project Name:SpringBootTest
 * File Name:RoleServiceImpl.java
 * Package Name:com.test.service.impl
 * Date:2017年8月30日下午3:58:08
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.RoleMapper;
import com.test.pojo.Role;
import com.test.service.RoleServiceI;

/**
 * ClassName:RoleServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月30日 下午3:58:08 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Service
public class RoleServiceImpl implements RoleServiceI {
	@Autowired
	private RoleMapper roleDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List getRoleList() {
		
		// TODO Auto-generated method stub
		return roleDao.getRoleList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getRoleById(Role role) {
		
		// TODO Auto-generated method stub
		return roleDao.getRoleById(role);
	}

	@Override
	public void delBefRp(Role role) {
		
		// TODO Auto-generated method stub
		roleDao.delBefRp(role);
	}

	@Override
	public int insRpTable(String pid,int rid) {
		
		// TODO Auto-generated method stub
		return roleDao.insRpTable(Integer.parseInt(pid),rid);
	}

	@Override
	public int roleDel(int rid) {
		
		// TODO Auto-generated method stub
		return roleDao.roleDel(rid);
	}

	@Override
	public Role getRoleByName(Role role) {
		
		// TODO Auto-generated method stub
		return roleDao.getRoleByName(role);
	}

	@Override
	public int addRoleAndGetRid(Role role) {
		
		// TODO Auto-generated method stub
		return roleDao.addRoleAndGetRid(role);
	}
	

}

