package com.king.Bigshow.actions;

import org.apache.struts2.ServletActionContext;

public class LoginoutAction {
	public String login_out(){
		ServletActionContext.getContext().getSession().clear();
		return "login_out";
}
}
