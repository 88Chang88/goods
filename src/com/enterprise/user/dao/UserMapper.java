package com.enterprise.user.dao;

import com.enterprise.common.annotation.MyBatisMappeer;
import com.enterprise.common.entity.User;

@MyBatisMappeer
public interface UserMapper {

	/**
	 * @param userName
	 * @return
	 */
	Integer countByUserName(String userName);
	
	/**
	 * @param user
	 */
	void insertUser(User user);
	
}
