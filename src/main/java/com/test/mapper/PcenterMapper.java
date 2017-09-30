package com.test.mapper;

import com.test.pojo.User;

public interface PcenterMapper {

	User getUserByName(String username);

	void updatePcent(User user);

	void updatePcentAndPass(User user);

	void updatePcentAndImg(User user);

	void updatePcentAndPassAndImg(User user);

}
