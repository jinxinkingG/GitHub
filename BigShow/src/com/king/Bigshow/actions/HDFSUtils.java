package com.king.Bigshow.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.MapFileOutputFormat;

import com.king.Bigshow.model.MyKeyValue;
import com.king.Bigshow.utils.ValueSortList;

import org.apache.hadoop.io.MapFile.Reader;
import org.apache.hadoop.io.Text;

public class HDFSUtils {
	private static Configuration conf = new Configuration();
	private static Path path = new Path(
			"hdfs://localhost:9000/output_news_map_2/");
	private static Set<String> allNoKeyword = new HashSet<String>();
	private static FileSystem fs = null;

	static {
		try {
			fs = path.getFileSystem(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static {
		allNoKeyword.add("新闻");
		allNoKeyword.add("新浪网");
		allNoKeyword.add("新浪");
		allNoKeyword.add("聚合");
		allNoKeyword.add("中国");
		allNoKeyword.add("视频");
		allNoKeyword.add("图片");
		allNoKeyword.add("图集");
		allNoKeyword.add("最新");
		allNoKeyword.add("阅读");
		try {
			fs = path.getFileSystem(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Integer[] getIdsByKeyword(String keyword) throws Exception {

		Reader reader = MapFileOutputFormat.getReaders(fs, path, conf)[0];
		Text value = (Text) reader.get(new Text(keyword), new Text());
		Set<Integer> set = new TreeSet<Integer>();
		String[] strs = value.toString().split(",");

		for (String str : strs) {
			set.add(Integer.parseInt(str));
		}
		
		return set.toArray(new Integer[0]);
	}
	public static ValueSortList getValues() throws Exception {
		path = new Path("hdfs://localhost:9000/output_1/part-00000");
		fs = path.getFileSystem(conf);
		FSDataInputStream is = fs.open(path);
		// 使用一个缓冲流或者内存流来整体读入数据
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String line = null;
		// 建立工具类
		ValueSortList list = new ValueSortList();
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			String[] strs = line.split("\t");
			System.out.println(strs.length);
			try {
				// 建立vo对象
				if (!allNoKeyword.contains(strs[0]) && strs[0].length() > 1) {
					MyKeyValue keyValue = new MyKeyValue(strs[0],
							Integer.parseInt(strs[1]));
					list.add(keyValue);
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

}
