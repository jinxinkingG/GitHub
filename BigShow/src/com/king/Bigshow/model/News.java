package com.king.Bigshow.model;

public class News {
	private Integer id;
	private String title;
	private String description;
	private String url;
	public News(){}
	public News(Integer id, String title, String description, String url) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
