package com.king.Bigshow.model;

public class MyKeyValue {
	private String key;
	private Integer value;
	public MyKeyValue(){}
	public MyKeyValue(String key,Integer value){
		this.key=key;
		this.value=value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public int compare(MyKeyValue other){
		if(this.value>=other.value) return 1;
		else {
			return -1;
		}
	}
	@Override
	public String toString(){
		return "MykeyValue [key="+key+",value="+value+"]\r\n";
	}
}
