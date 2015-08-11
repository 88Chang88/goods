package com.enterprise.common.entity;

import java.io.Serializable;

/**
 *
 * Description：TODO
 *
 * @author Chang
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uid;//主键
	
	private String loginname;//登录名
	
	private String loginpass;//登陆密码
	
	private String email;//邮箱
	
	private int status;//状态，1表示已激活，0表示未激活
	
	private String activationCode;//激活码，它是唯一值！即每个用户的激活码是不同的
	
	/*注册表单补充信息*/
	private String reloginpass;
	
	private String verifyCode;

	public User() {
		super();
	}

	public User(String uid, String loginname, String loginpass, String email,
			int status, String activationCode) {
		super();
		this.uid = uid;
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.email = email;
		this.status = status;
		this.activationCode = activationCode;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass="
				+ loginpass + ", email=" + email + ", status=" + status
				+ ", activationCode=" + activationCode + ", reloginpass="
				+ reloginpass + ", verifyCode=" + verifyCode + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpass() {
		return loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getReloginpass() {
		return reloginpass;
	}

	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	

}
