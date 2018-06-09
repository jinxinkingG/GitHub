package com.king.Bigshow.actions;

import com.opensymphony.xwork2.ActionSupport;

public class AnalyisAction extends ActionSupport{
	public String execute() throws Exception{
		String[] args={"a","b"};
		new MapperReduceUtils();
		MapperReduceUtils.main(args);
		return SUCCESS;
	}

}
