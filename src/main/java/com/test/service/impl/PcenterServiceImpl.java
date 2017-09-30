package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.PcenterMapper;
import com.test.pojo.User;
import com.test.service.PcenterServiceI;
import com.test.util.Md5Util;
@Service
public class PcenterServiceImpl implements PcenterServiceI {
	
	@Autowired
	private PcenterMapper pcenterDao;

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return pcenterDao.getUserByName(username);
	}

	@Override
	public void updatePcent(User user) {
		
		// TODO Auto-generated method stub
		pcenterDao.updatePcent(user);
	}

	@Override
	public void updatePcentAndPass(User user) {
		
		// TODO Auto-generated method stub
		Md5Util md5Util = new Md5Util();
		@SuppressWarnings("static-access")
		String md5Password = md5Util.MD5(user.getPassword());
		user.setPassword(md5Password);
		pcenterDao.updatePcentAndPass(user);
	}

	@Override
	public void updatePcentAndImg(User user) {
		
		// TODO Auto-generated method stub
		pcenterDao.updatePcentAndImg(user);
	}

	@Override
	public void updatePcentAndPassAndImg(User user) {
		
		// TODO Auto-generated method stub
		Md5Util md5Util = new Md5Util();
		@SuppressWarnings("static-access")
		String md5Password = md5Util.MD5(user.getPassword());
		user.setPassword(md5Password);
		pcenterDao.updatePcentAndPassAndImg(user);
	}

}
