package com.king.Bigshow.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.king.Bigshow.Interfaces.INewsDAO;
import com.king.Bigshow.model.News;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CollectAction extends ActionSupport{
	private static Configuration conf=new Configuration();
	private static FileSystem fs;
	private static Path path;
	private String pathAdd;
	private static int id = 1;
	private static List<String> allWaitUrl = new ArrayList<>();
	private static Set<String> allOverUrl = new HashSet<>();
	private static Map<String, Integer> allUrlDepth = new HashMap<>();
	private  static int maxDepthS;
	private String maxDepth;
	private static Set<String> news=new HashSet<String>(); 
	ActionContext context=ActionContext.getContext();
	public String getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(String maxDepth) {
		this.maxDepth = maxDepth;
	}
	private static Object obj = new Object();
	private static final int MAX_THREAD = 10;
	private static int count = 0;
	public String getPathAdd() {
		return pathAdd;
	}
	public void setPathAdd(String pathAdd) {
		this.pathAdd = pathAdd;
	}
	public static void parseUrl(String strUrl, int depth) {
		if (!(allOverUrl.contains(strUrl) || depth > maxDepthS)) {
			System.out.println("当前执行的" + Thread.currentThread().getName()+ "爬虫线程处理爬取：" + strUrl);
			try{
				Document doc = Jsoup.connect(strUrl).get();
				// 通过这个doc来接收返回的结果
				// 提取有效的title和description
				String title = doc.title();
				Element descE = doc.getElementsByAttributeValue("name",
						"description").first();
				String desc = descE.attr("content");

				// 如果有效，则进行保存
				if (title != null && desc != null && !title.trim().equals("")
						&& !desc.trim().equals("")) {

					// 还需要生成一个id，以便放到数据库中，然后把id也加入到HDFS里，便于后续构建索引。
					News news = new News();
					news.setId(id++);
					news.setTitle(title);
					news.setDescription(desc);
					news.setUrl(strUrl);
					ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
					INewsDAO inews=(INewsDAO)ac.getBean("newsInterface");
					inews.doCreate(news);
					path=new Path("hdfs://localhost:9000/sina_news_input/"+System.currentTimeMillis()+".txt");
					fs=path.getFileSystem(conf);
					FSDataOutputStream os=fs.create(path);
					os.writeUTF(news.getId()+"\r\n"+title+"\r\n"+desc);
					os.close();
					// 解析所有的超连接
					Elements aEs = doc.getElementsByTag("a");

					if (aEs != null && aEs.size() > 0) {
						for (Element aE : aEs) {
							String href = aE.attr("href");
							if ((href.startsWith("http:") || href
									.startsWith("https:"))
									&& href.contains("news.sina.com.cn")) {
								addUrl(href, depth + 1);
							}
						}
					}
				}

			}
			catch (Exception e) {
			e.printStackTrace();
		}
		allOverUrl.add(strUrl);
		System.out.println(strUrl + " 爬取完成，已经爬取的内容量为：" + allOverUrl.size()
		+ " , 剩余要爬取量为：" + allWaitUrl.size());
		if (allWaitUrl.size() > 0) {
			synchronized (obj) {
				obj.notify();
			}
		} else{
			System.out.println("爬取结束...");
			System.exit(0);
		}
	}
	}
	public static synchronized void addUrl(String href,int depth){
		allWaitUrl.add(href);
		if (!allUrlDepth.containsKey(href)) {
			allUrlDepth.put(href, depth + 1);
		}
	}
	public static synchronized String getUrl(){
		String nextUrl=allWaitUrl.get(0);
		allWaitUrl.remove(0);
		return nextUrl;
	}
	public String execute() throws Exception{
		maxDepthS=Integer.parseInt(maxDepth);
		addUrl(pathAdd, 0);
		for (int i = 0; i < MAX_THREAD; i++) {
			new CollectAction().new MyThread().start();
		}
		context.getSession().put("result","爬取中");
		return SUCCESS;
	}
	public String refresh()throws Exception{
		context.getSession().put("result"," 爬取完成，已经爬取的内容量为：" + allOverUrl.size()
		+ " , 剩余要爬取量为：" + allWaitUrl.size());
		return SUCCESS;
	}
	public String Clear()throws Exception{
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		Thread[] threads=new Thread[group.activeCount()];
		group.enumerate(threads);
		System.out.println("现在活跃进程数："+group.activeCount());
/*		for(int i=1;i<threads.length;i++)
			threads[i].interrupt();*/
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		INewsDAO inews=(INewsDAO)ac.getBean("newsInterface");
		inews.ClearTableNews();
		context.getSession().put("result","爬取终止");
		return SUCCESS;
	}
	public class MyThread extends Thread{
		@Override
		public void run(){
			while(true){
				if(allWaitUrl.size()>0){
					String url=getUrl();
					parseUrl(url, allUrlDepth.get(url));
				}else {
					System.out.println("当前线程准备就绪，等待连接爬取：" + this.getName());
					count++;
					synchronized (obj) {
						try {
							obj.wait();
						}catch (Exception e){
							// TODO: handle exception
							
							e.printStackTrace();
						}
					}
					count--;
				}
			}
		}
	}
}