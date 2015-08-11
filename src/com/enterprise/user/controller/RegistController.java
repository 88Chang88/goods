package com.enterprise.user.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enterprise.common.content.GoodsContents;
import com.enterprise.common.controller.BaseController;
import com.enterprise.common.entity.User;
import com.enterprise.common.until.UidUtils;
import com.enterprise.common.until.VelidateCode;
import com.enterprise.user.service.UserService;

/**
 *
 * Description：TODO
 *
 * @author Chang
 *
 */
@Controller
@RequestMapping("/regist")
public class RegistController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/toregist",method=RequestMethod.GET)
	public String toRegist(){
		return "user/regist";
	}
	
	@RequestMapping(value="/verfiycode",method=RequestMethod.GET)
	public void getVerfiyCode(HttpServletRequest request,HttpServletResponse response){
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存
		response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);
        //获得seesion
        HttpSession session = request.getSession();
        //创建验证码
        VelidateCode vCode=new VelidateCode(300, 75, 5, 50);
        //将验证码字符放入session中
        session.setAttribute("code", vCode.getCode());
        //将验证码图片返回给浏览器
        try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/validateLoginname",method=RequestMethod.GET)
	public String velidateLoginname(String loginname){
		if(userService.velidateLoginname(loginname)){
			//用户名不存在
			return GoodsContents.SUCCESS;
		}
		//用户名已存在
		return GoodsContents.ERROR;
	}
	
	@ResponseBody
	@RequestMapping(value="/validateVCode",method=RequestMethod.POST)
	public String velidateVCode(HttpServletRequest request,String vCode){
		//
		String sessionVCode = (String) request.getSession().getAttribute("code");
		//
		if(vCode.equals(sessionVCode)){
			return GoodsContents.SUCCESS;
		}else{
			return GoodsContents.ERROR;
		}
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(User user){
		//获得注册信息
		System.out.println("-->"+user);
		//服务端验证用户信息
		//TODO
		//补充用户缺省信息
		user.setUid(UidUtils.generate());
		user.setActivationCode(UidUtils.generate()+UidUtils.generate());
		//插入用户信息
//		userService.insertUser(user);
		return "msg";
	}

}
