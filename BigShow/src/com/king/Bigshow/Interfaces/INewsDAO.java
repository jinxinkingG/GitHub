package com.king.Bigshow.Interfaces;

import java.util.List;

import com.king.Bigshow.model.News;


public interface INewsDAO {
	public void doCreate(News news) throws Exception;
	public News findById(int id) throws Exception;
	public List<News> findByids(Integer[] ids) throws Exception;
	public void ClearTableNews() throws Exception;
}
