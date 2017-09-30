/**
 * Project Name:SpringBootTest
 * File Name:RoleController.java
 * Package Name:com.test.controller
 * Date:2017年8月30日下午3:56:51
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.pojo.Role;
import com.test.service.RoleServiceI;

/**
 * ClassName:RoleController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月30日 下午3:56:51 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
public class RoleController {

	@Autowired
	private RoleServiceI rolerService;

	@RequestMapping("/roleManager")
	public String roleManager(HttpServletRequest request) {

		@SuppressWarnings("rawtypes")
		List roleList = rolerService.getRoleList();

		request.setAttribute("roleList", roleList);

		return "sys/roleManager";
	}

	/**
	 * 
	 * roleToupd:(更新角色 信息回显). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @since JDK 1.7
	 */
	@RequestMapping("/roleToupd")
	public void roleToupd(Role role, HttpServletResponse response) {
		@SuppressWarnings("rawtypes")
		List roleById = rolerService.getRoleById(role);
		JSONArray json = JSONArray.fromObject(roleById);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	@RequestMapping("/roleUpStart")
	public void roleUpStart(HttpServletResponse response,Role role,String pids){
		String[] splitPid = pids.split(",");
		int i=0;
		rolerService.delBefRp(role);//修改角色权限前先删除原有权限；
		for (String pid : splitPid) {
			i=rolerService.insRpTable(pid ,role.getRid()); //逐条插入权限
		}
		try {
			response.getWriter().write(i+"");
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	@RequestMapping("/roleDel")
	public void roleDel(HttpServletResponse response,String ids){
		int i=0;
		String[] splitRid = ids.split(",");
		for (String rid : splitRid) {
			i=rolerService.roleDel(Integer.parseInt(rid));
		}
		try {
			response.getWriter().write(i+"");
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	/**
	 * 
	 * roleYanZ:(添加角色时验证角色是否存在). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param response
	 * @param role
	 * @since JDK 1.7
	 */
	@RequestMapping("/roleYanZ")
	public void roleYanZ(HttpServletResponse response,Role role){
		
		String replaceRname = role.getRname().replace(" ", "");
		role.setRname(replaceRname);
		
		Role roleByName=rolerService.getRoleByName(role);
		
		try {
			if(replaceRname.equals("")||replaceRname==null){
				response.getWriter().write("2");
			}else if(roleByName==null && !replaceRname.equals("")){
				response.getWriter().write("0");
			}
			else{
				response.getWriter().write("1");
			}
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	@RequestMapping("/addRole")
	public void addRole(HttpServletResponse response,Role role,String pidss){
		
		@SuppressWarnings("unused")
		int j=rolerService.addRoleAndGetRid(role); //插入  角色 并返回 主键   id
		
		int rid=role.getRid();
		
		int i=0;
		
		String[] splitPid = pidss.split(",");
		
		for (String pid : splitPid) {
			i= rolerService.insRpTable(pid, rid);
		}
		
		try {
			response.getWriter().write(i+"");
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
