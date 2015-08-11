package com.enterprise.common.until;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;



public class MailUtils {
	
	private static Session createSession(String host,final String userName,final String password){
		
		Properties props =new Properties();
		//设置邮件服务器地址
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "25");
		//设置邮件服务器是否需要登录认证
		props.setProperty("mail.smtp.auth", "true");
		//创建认证器
		Authenticator auth =new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		//创建Session对象
		return Session.getInstance(props,auth);
	}
	
	private static MimeMessage createMessage(Session session,String from,String to,String subject,String content) throws AddressException, MessagingException{
		
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress(from));
		//设置收件人
		message.addRecipient(RecipientType.TO,new InternetAddress(to));
		//设置邮件主题
		message.setSubject(subject);
		//设置邮件内容及内容的MIME类型
		message.setContent(content, "text/html;charset=utf-8");
		//
		return message;
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @return
	 */
	public static boolean sendToOne(String from,String to,String subject,String content){

		try {
			
			String host="smtp.163.com";
			String userName="chang1050833599@163.com";
			String password="20012991cc";
			Session session=MailUtils.createSession(host, userName, password);
			
			MimeMessage message = MailUtils.createMessage(session, from, to, subject, content);
			
			Transport.send(message);
			
			return true;
			
		} catch (AddressException e) {
			return false;
		} catch (MessagingException e) {
			return false;
		}
		
		
	}

}
