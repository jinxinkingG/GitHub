package com.king.Bigshow.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapFileOutputFormat;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import jeasy.analysis.MMAnalyzer;


public class MapperReduceUtils extends Configured implements Tool{
	public static class MapClass extends MapReduceBase implements Mapper<LongWritable,Text,Text,Text>
	{
		private static Pattern p;
		static{
			System.out.println("开始Map操作");
			p=Pattern.compile("\\d+");
		}
		private int id;
		private int line=1;
		private static MMAnalyzer mm=new MMAnalyzer();
		private Text word=new Text();
		public void map(LongWritable key,Text value,OutputCollector<Text, Text> output,Reporter reporter) throws IOException{
			if(line==1){
				line++;
				Matcher m=p.matcher(value.toString());
				if(m.find()){
					id=Integer.parseInt(m.group());
				}
			}else {
				String tempStr=value.toString();
				String[] results=mm.segment(tempStr, "|").split("\\|");
				for(String temp:results){
					word.set(temp.toLowerCase());
					output.collect(word, new Text(id+""));
				}
			}
		}
	}
	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text>{
		static {
			System.out.println("开始Reduce操作");
		}
		public void reduce(Text key,Iterator<Text> values,OutputCollector<Text, Text> output,Reporter reporter)throws IOException{
			StringBuilder result=new StringBuilder();
			while(values.hasNext()){
				String temp=values.next().toString();
				if(!result.toString().contains(temp+",")){
					result.append(temp+",");
				}
			}
			output.collect(key, new Text(result.substring(0,result.length()-1)));
			System.out.println(key+"--->"+result);
		}
	}
	static int printUsage(){
		System.out.println("wordcount [-m <maps>] [-r <reduces>] <input> <output>");
		ToolRunner.printGenericCommandUsage(System.out);
		return -1;
	}
	public int run(String[] args)throws Exception{
		JobConf conf=new JobConf(getConf(),MapperReduceUtils.class);
		conf.setJobName("wordcount");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(MapClass.class);
		conf.setReducerClass(Reduce.class);
		conf.setCombinerClass(Reduce.class);
		List<String> other_args=new ArrayList<>();
		for (int i = 0; i < args.length; ++i) {
			try {
				if ("-m".equals(args[i])) {
					conf.setNumMapTasks(Integer.parseInt(args[++i]));
				} else if ("-r".equals(args[i])) {
					conf.setNumReduceTasks(Integer.parseInt(args[++i]));
				} else {
					other_args.add(args[i]);
				}
			} catch (NumberFormatException except) {
				System.out.println("ERROR: Integer expected instead of "
						+ args[i]);
				return printUsage();
			} catch (ArrayIndexOutOfBoundsException except) {
				System.out.println("ERROR: Required parameter missing from "
						+ args[i - 1]);
				return printUsage();
		}
	}
		if (other_args.size() != 2) {
			System.out.println("ERROR: Wrong number of parameters: "
					+ other_args.size() + " instead of 2.");
			return printUsage();
		}
		conf.setOutputFormat(MapFileOutputFormat.class);
		FileInputFormat.setInputPaths(conf, other_args.get(0));
		FileOutputFormat.setOutputPath(conf, new Path(other_args.get(1)));

		JobClient.runJob(conf);
		return 0;
	}
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MapperReduceUtils(),
				new String[] { "hdfs://localhost:9000/sina_news_input/",
						"hdfs://localhost:9000/output_news_map/" });
		System.exit(res);
	}

}
