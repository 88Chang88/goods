package com.enterprise.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.common.entity.User;
import com.enterprise.user.dao.UserMapper;
import com.enterprise.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean velidateLoginname(String loginname) {
		//
		Integer count = userMapper.countByUserName(loginname);
		//
		return count == 0;
	}

	@Override
	public boolean insertUser(User user) {
		userMapper.insertUser(user);
		return true;
	}

}
