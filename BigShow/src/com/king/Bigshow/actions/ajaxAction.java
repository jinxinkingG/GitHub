package com.king.Bigshow.actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.king.Bigshow.model.MyKeyValue;
import com.king.Bigshow.utils.ValueSortList;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ajaxAction extends ActionSupport implements ServletResponseAware{
	private HttpServletResponse response; 
	public String execute() throws Exception{
		ValueSortList list = HDFSUtils.getValues();

		JSONObject obj = new JSONObject();
		

		JSONObject titleObj = new JSONObject();
		titleObj.put("text", "新浪新闻热词分析");
		obj.put("title", titleObj);

		JSONObject tooltipsObj = new JSONObject();
		tooltipsObj.put("trigger", "axis");
		obj.put("tooltip", tooltipsObj);

		JSONObject legendObj = new JSONObject();
		legendObj.put("data", "词频");
		obj.put("legend", legendObj);

		obj.put("yAxis", new JSONObject());

		JSONObject xObj = new JSONObject();
		JSONArray seArr = new JSONArray();
		JSONObject seObj = new JSONObject();
		seObj.put("name", "词频");
		seObj.put("type", "bar");
		JSONArray xDataArr = new JSONArray();
		JSONArray seDataArr = new JSONArray();

		for (MyKeyValue kv : list.getList()) {
			xDataArr.add(kv.getKey());
			seDataArr.add(kv.getValue());
		}

		xObj.put("data", xDataArr);
		seObj.put("data", seDataArr);
		seArr.add(seObj);

		obj.put("xAxis", xObj);
		obj.put("series", seArr);


		// 结果返回需要通过PrintWriter来输出
		// 需要先处理返回乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(obj.toString());
		writer.close();
		return SUCCESS;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
}
