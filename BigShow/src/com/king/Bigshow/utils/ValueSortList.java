package com.king.Bigshow.utils;

import java.util.ArrayList;
import java.util.List;

import com.king.Bigshow.model.MyKeyValue;

public class ValueSortList {
	private List<MyKeyValue> list=new ArrayList<>();
	public void add(MyKeyValue keyValue){
		boolean flag=false;
		for(int i=0;i<list.size();i++){
			if(keyValue.compare(list.get(i))>0){
				list.add(i,keyValue);
				flag=true;
				break;
			}
		}
		if(flag==false){
			list.add(keyValue);
		}
		if(list.size()>10){
			list.remove(10);
		}
	}
	public List<MyKeyValue> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "ValueSortList [list=" + list + "]";
	}
}
