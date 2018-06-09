package com.king.Bigshow.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.king.Bigshow.model.MyKeyValue;
import com.king.Bigshow.utils.ValueSortList;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ajaxCloudAction extends ActionSupport implements ServletResponseAware{
	private HttpServletResponse response;
	public String execute() throws Exception{
	ValueSortList list = HDFSUtils.getValues();

	JSONArray array = new JSONArray();
	for (MyKeyValue kv : list.getList()) {
		JSONObject obj = new JSONObject();
		obj.put("name", kv.getKey());
		obj.put("value", kv.getValue());
		array.add(obj);
	}
	
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
	PrintWriter writer = response.getWriter();
	writer.print(array.toString());
	writer.close();
	return SUCCESS;
}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
}


