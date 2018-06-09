package com.king.Bigshow.actions;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.king.Bigshow.Interfaces.INewsDAO;
import com.king.Bigshow.model.News;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String execute() throws Exception{
			try {
				Integer[] ids=HDFSUtils.getIdsByKeyword(keyword);
				
				ApplicationContext acc=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
				INewsDAO inews=(INewsDAO)acc.getBean("newsInterface");
				List<News> allnews=inews.findByids(ids);
				ActionContext ac=ActionContext.getContext();
				ac.getSession().put("allNews", allnews);
				
				for(int i=0;i<allnews.size();i++){
					System.out.println(allnews.get(i).getId());
				}
				return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return SUCCESS;
	}

}
