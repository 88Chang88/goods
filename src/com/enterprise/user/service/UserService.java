package com.enterprise.user.service;

import com.enterprise.common.entity.User;


public interface UserService {
	
	boolean velidateLoginname(String loginname);
	
	boolean insertUser(User user);

}
