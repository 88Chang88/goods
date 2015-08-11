package com.enterprise.demo.dao;

import com.enterprise.common.annotation.MyBatisMappeer;
import com.enterprise.demo.entity.Admin;

@MyBatisMappeer
public interface AdminMapper {

	Admin findById(String adminId);

}
