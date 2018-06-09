package com.king.Bigshow.actions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.king.Bigshow.Interfaces.AccountInterface;
import com.king.Bigshow.model.AccountBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
	private String username;
	private String password;
	private String cfmpwd;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String execute(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountInterface account=(AccountInterface)app.getBean("accountInterface");
		AccountBean acc=new AccountBean(username,password,"user");
		ActionContext acc1=ActionContext.getContext();
		acc1.getSession().put("username", username);
		acc1.getSession().put("userType",acc.getUserType());
		account.insert(acc);
		return SUCCESS;
	}
}
