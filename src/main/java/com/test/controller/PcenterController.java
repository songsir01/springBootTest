package com.test.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.test.pojo.User;

@Controller
public class PcenterController extends BaseController{
	
	public Logger logger = LogManager.getLogger(PcenterController.class);


	@RequestMapping("/pcenter")
	public String pcenter(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("user");
		User user = pcenterService.getUserByName(username);
		request.setAttribute("user", user);
		logger.info("个人中心页面："+user);
		return "sys/pcenter";
	}

	@RequestMapping("/updatePcenter")
	public String updatePcenter(HttpServletRequest request,
			HttpServletResponse response, MultipartFile imageUrl,User user) {
		
		//System.out.println(user);
		if(imageUrl.isEmpty()){
			logger.info("没有上传图片");
			if(user.getPassword().equals("")){
				pcenterService.updatePcent(user);
				return "doing";
			}else{
				pcenterService.updatePcentAndPass(user);
				
				request.getSession().removeAttribute("user");

				request.getSession().removeAttribute("rid");
				
				return "doing";
			}
		}else {

			String fileName = imageUrl.getOriginalFilename();
			//String suffixName = fileName.substring(fileName.lastIndexOf("."));//文件后缀名

			String realPath = request.getServletContext().getRealPath("/style/userimg/");
			File dest = new File(realPath + fileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				imageUrl.transferTo(dest);
				user.setImg(fileName);
				if(user.getPassword().equals("")){
					pcenterService.updatePcentAndImg(user);
					return "doing";
				}else{
					pcenterService.updatePcentAndPassAndImg(user);
					
					request.getSession().removeAttribute("user");

					request.getSession().removeAttribute("rid");
					
					return "doing";
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("个人信息修改");
		return "err";
	}

}
