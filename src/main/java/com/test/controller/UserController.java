/**
 * Project Name:SpringBootTest
 * File Name:UserController.java
 * Package Name:com.test.controller
 * Date:2017年8月24日下午5:39:19
 * Copyright (c) 2017, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.pojo.Role;
import com.test.pojo.User;
import com.test.util.PageUtil;
import com.test.util.vo.UserVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassName:UserController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年8月24日 下午5:39:19 <br/>
 * 
 * @author SongYapeng
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
public class UserController extends BaseController{
	
	public Logger logger = LogManager.getLogger(UserController.class);
	/**
	 * 
	 * regist:(跳到注册页面). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/regist")
	public String regist() {
		logger.info("register..页面");
		return "reg";
	}

	/**
	 * 
	 * userunique:(验证用户名是否存在). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/userunique")
	public void userunique(User user, HttpServletResponse response) {

		String replaceName = user.getUsername().replace(" ", "");

		user.setUsername(replaceName);
		@SuppressWarnings("rawtypes")
		List useruniqueList = userService.getuniqueList(user);

		if (useruniqueList.isEmpty()) {
			try {
				response.getWriter().write("0");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		logger.info("注册验证用户是否已经存在"+useruniqueList);
	}

	/**
	 * 
	 * regNext:(用户注册). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param model
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/regNext")
	public void regNext(User user, Model model, HttpServletResponse response) {
		Matcher matcher = Pattern.compile("[A-Za-z0-9\\-]{3,16}$").matcher(user.getUsername());
		if (!matcher.matches()) {
			try {
				response.getWriter().write("4");// 用户名不合法
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			@SuppressWarnings("rawtypes")
			List useruniqueList = userService.getuniqueList(user);
			if (useruniqueList.isEmpty()) {
				try {
					int uid = userService.userAddAndGetId(user);
					response.getWriter().write(uid + "");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().write("3");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		logger.info("注册");

	}

	@RequestMapping("/login")
	public void login(User user, HttpServletResponse response, HttpServletRequest request) {

		User userLogin = userService.userLogin(user);

		try {
			if (userLogin == null) {

				response.getWriter().write("0"); // 用户名不存在或者密码错误！
			} else {
				request.getSession().setAttribute("user", userLogin.getUsername());
				request.getSession().setAttribute("rid", userLogin.getRid());
				response.getWriter().write("1");// 密码正确！
				request.setAttribute("a", "bbb");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("登录");

	}

	@RequestMapping("/zhuye")
	public String zhuye() {
		logger.info("进入主页");
		return "sys/index";
	}

	/**
	 * 
	 * notLogin:(退出当前用户). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/notLogin")
	public void notLogin(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().removeAttribute("user");

		request.getSession().removeAttribute("rid");

		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("退出登录");
	}

	/**
	 * 
	 * isLogin:(防止通过url直接访问页面，判断是否登录). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param request
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/isLogin")
	public void isLogin(HttpServletRequest request, HttpServletResponse response) {

		Object attribute = request.getSession().getAttribute("user");

		if (attribute == null) {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("判断是否登录");

	}

	/**
	 * 
	 * roleByRid:(根据角色rid 查找 改角色具有哪些权限). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param rid
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/roleByRid")
	public void roleByRid(Role role, HttpServletResponse response) {

		@SuppressWarnings("rawtypes")
		List perList = userService.getRoleByRid(role);

		JSONArray json = JSONArray.fromObject(perList);
		
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("根据角色查找权限："+perList);

	}

	/**
	 * 
	 * userManager:(用户列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param request
	 * @param userVo
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/userManager")
	public String userManager(HttpServletRequest request, UserVo userVo) {

		String userName = null;
		String replaceName = null;
		userName = request.getParameter("keyWord");
		String pageSize = request.getParameter("pageSize");
		if (pageSize == null || pageSize.equals("")) {
			pageSize = "10";
		}
		Integer inPageSize = Integer.valueOf(pageSize);
		if (userName != null) {
			replaceName = userName.replace(" ", "");
			userVo.setUserName("%" + replaceName + "%");
		}
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		int userCount = userService.getUserCount(userVo);

		PageUtil pUtil = new PageUtil(page, inPageSize, userCount);
		userVo.setPageSize(pUtil.getPagesize());
		userVo.setStartPage((pUtil.getPageindex() - 1) * pUtil.getPagesize());

		// 角色列表
		@SuppressWarnings("rawtypes")
		List roleList = userService.getRoleList();
		request.setAttribute("roleList", roleList);
		@SuppressWarnings("rawtypes")
		List userList = userService.getUserList(userVo);
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("prevPage", pUtil.getPrevpage());
		request.setAttribute("nextPage", pUtil.getNextpage());
		request.setAttribute("lastPage", pUtil.getLastpage());
		request.setAttribute("keyWord", replaceName);

		logger.info("用户列表");
		return "sys/userManager";
	}

	/**
	 * 
	 * userDel:(删除用户). <br/>
	 * 
	 * @author SongYapeng
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/userDel")
	public void userDel(HttpServletResponse response, HttpServletRequest request) {

		String ids = request.getParameter("ids");
		String[] splitIds = ids.split(",");
		int i = 0;
		for (String uid : splitIds) {
			i = userService.userDel(Integer.parseInt(uid));
		}
		try {
			if (i > 0) {
				response.getWriter().write("1");
			} else {
				response.getWriter().write("0");
			}
		} catch (IOException e) {

			e.printStackTrace();

		}
		logger.info("删除用户");
	}

	/**
	 * 
	 * toupd:(数据回显). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/toupd")
	public void toupd(User user, HttpServletResponse response) {
		User userByUid = userService.userByUid(user);
		JSONObject json = JSONObject.fromObject(userByUid);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(" 根据id查找用户"+userByUid);
	}

	/**
	 * 
	 * upStart:(更新操作). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param response
	 * @param user
	 * @since JDK 1.7
	 */
	@RequestMapping("/upStart")
	public void upStart(HttpServletResponse response, User user) {
		if (user.getPassword().equals("") || user.getPassword() == null) {
			int i = userService.userUpdate(user);
			try {
				if (i > 0) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			int i = userService.userPassUpdate(user);
			try {
				if (i > 0) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("更新操作");

	}
	
	@RequestMapping("/caozuo1")
	public String caozuo1(){
		logger.info("caozuo1");
		return "sys/caozuo1";
	}
	@RequestMapping("/caozuo2")
	public String caozuo2(){
		logger.info("caozuo2");
		return "sys/caozuo2";
	}
	@RequestMapping("/caozuo3")
	public String caozuo3(){
		logger.info("caozuo3");
		return "sys/caozuo3";
	}
	@RequestMapping("/ceshiyixia")
	public String ceshiyixia(){
		logger.info("ceshiyixia");
		return "sys/ceshiyixia";
	}

}
