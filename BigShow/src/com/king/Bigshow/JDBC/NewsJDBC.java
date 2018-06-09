package com.king.Bigshow.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.king.Bigshow.Interfaces.INewsDAO;
import com.king.Bigshow.model.News;

public class NewsJDBC implements INewsDAO{
	@Autowired
    @Qualifier("dataSource")
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource){    
        this.dataSource=dataSource;
    }
	@Override
	public void ClearTableNews() throws Exception{
		String sql="truncate table news";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.execute(sql);
	}
	@Override
	public void doCreate(News news) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into news(id,title,description,url) values(?,?,?,?)";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, news.getId(),news.getTitle(),news.getDescription(),news.getUrl());
	}
	@Override
	public News findById(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql="select id,title,description,url from news where id=?";
		final News news=new News();
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet arg0) throws SQLException {
				// TODO Auto-generated method stub
				news.setId(arg0.getInt("id"));
				news.setTitle(arg0.getString("title"));
				news.setDescription(arg0.getString("description"));
				news.setUrl(arg0.getString("url"));
			}
		});
		return news;
	}
	@Override
	public List<News> findByids(Integer[] ids) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder("select id,title,description,url from news where id in(");
		if(ids!=null&&ids.length>0){
			for(int id:ids){
				sql.append(id);
				sql.append(",");
			}			
			List<News> list=new ArrayList<>();
			String resultSQL=sql.substring(0,sql.length()-1)+")";
			System.out.println(resultSQL+"\n\n\n\n\n\n\n\n\n\n\n\n");
			JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
			jdbcTemplate.query(resultSQL,new RowCallbackHandler() {		
				@Override
				public void processRow(ResultSet arg0) throws SQLException {
					// TODO Auto-generated method stub
					News news=new News();
					news.setId(arg0.getInt("id"));
					news.setTitle(arg0.getString("title"));
					news.setDescription(arg0.getString("description"));
					news.setUrl(arg0.getString("url"));
					list.add(news);
					System.out.println(news.getId()+news.getTitle()+"\n");
				}
			});
			return list;
		}else {
			return null;
		}
	}


	
}
