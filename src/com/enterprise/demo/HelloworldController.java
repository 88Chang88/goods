package com.enterprise.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enterprise.common.controller.BaseController;

@Controller
@RequestMapping("/hello")
public class HelloworldController extends BaseController{
	
	@RequestMapping(value="/tohello",method=RequestMethod.GET)
	public String  tohelloworld() {
		return "user/regist";
	}

}
