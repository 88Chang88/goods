package com.enterprise.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.enterprise.common.until.MailUtils;
import com.enterprise.common.until.UidUtils;
import com.enterprise.demo.dao.AdminMapper;

public class TestMyBatis {
	
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//@Test
	public void test1(){
		
		AdminMapper adminMapper=ac.getBean("adminMapper",AdminMapper.class);
		
		System.out.println(adminMapper.findById("a1"));

	}
	
	
	public void testSendMail(){
		String from="13067881520@163.com";
		String to="1050833599@qq.com";
		String subject="JavaTest";
		String content="你好，程序测试成功！<a href='http://www.baidu.com'>百度一下</a>";
		MailUtils.sendToOne(from, to, subject, content);
	}
	
	@Test
	public void testUidUtils(){
		String uid = UidUtils.generate();
		System.out.println("UID-->"+uid);
	}

}
