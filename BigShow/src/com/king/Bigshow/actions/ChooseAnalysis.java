package com.king.Bigshow.actions;

import com.opensymphony.xwork2.ActionSupport;

public class ChooseAnalysis extends ActionSupport{
	public String execute(){
		return SUCCESS;
	}
	public String analysis() throws Exception{
		String[] args={"a","b"};
		new MapperReduceUtils();
		MapperReduceUtils.main(args);
		return SUCCESS;
	}

}
