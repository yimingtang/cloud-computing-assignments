package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.hbase.KeyValue; 
import cn.edu.nju.software.mr.util.DateUtils;
import cn.edu.nju.software.mr.util.HDFSTest;
import cn.edu.nju.software.mr.util.TableUtil;


public class RepeatDetectJob {
	public void execute() throws IOException, InterruptedException,	ClassNotFoundException {
		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "PaperSort");
		job.setJarByClass(RepeatDetectJob.class);
		
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("paper", scan, RepearDectectMapper.class,
				IntWritable.class, Paper.class, job);
		job.setReducerClass(RepearDectectReducer.class);
		job.setPartitionerClass(RepearDectectPartition.class);
		job.setNumReduceTasks(1);
		Path path =new Path("hdfs://localhost:9000/result/RepeatDetect");
		HDFSTest.deleteFile(path, true);
		FileOutputFormat.setOutputPath(job, path);
		
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}

	public static class RepearDectectMapper extends TableMapper<IntWritable, Paper> {
		
		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
			IntWritable keyInt = new IntWritable();
			Paper paper = new Paper();
			
			String rowKey=new String(value.getRow());
			paper.setKey(rowKey);
			CellScanner cs=value.cellScanner();
			
			while(cs.advance()){
				Cell cell=cs.current();
				String qualifiar=new String(CellUtil.cloneQualifier(cell));
				String columnValue=new String(CellUtil.cloneValue(cell));
				
				if("year".equals(qualifiar)){
					paper.setYear(columnValue);
				}else if("conference".equals(qualifiar)){
					paper.setConference(columnValue);
				}else if("title".equals(qualifiar)){
					paper.setTitle(columnValue);
				}else if("author".equals(qualifiar)){
					paper.setAuthor(columnValue);
				}else if("typeDate".equals(qualifiar)){
					paper.setTypeDate(columnValue);
				}else if("typeUser".equals(qualifiar)){
					paper.setTypeUser(columnValue);
				}
			}
			
			
			int hashkey=paper.getTitle().hashCode();
			keyInt.set(hashkey);
			context.write(keyInt, paper);
		}
	}
	
	public static class RepearDectectPartition extends Partitioner<IntWritable,Paper>{

		@Override
		public int getPartition(IntWritable arg0, Paper arg1, int arg2) {
			// TODO Auto-generated method stub
			return arg0.get()%arg2;
		}
	}
	
	public static class RepearDectectReducer extends Reducer<IntWritable, Paper, Text, Text> {
		public void reduce(IntWritable key, Iterable<Paper> values, Context context)
				throws IOException, InterruptedException {
			Text keyText=new Text();
			Text valueText=new Text();
			int i=0;
			List<List<Paper>> paperLL=new ArrayList<List<Paper>>();
			for(Paper paper:values){
				Paper tmppaper=new Paper(paper);
				
				if(i==0){
					ArrayList<Paper> paperList=new ArrayList<Paper>();
					paperList.add(tmppaper);
					paperLL.add(paperList);
					i++;
					continue;
				}
				
				int llsize=paperLL.size();
				boolean mark=false;
				for(int j=0;j<llsize && !mark;j++){
					List<Paper> paperTmpList=paperLL.get(j);
					if(paperTmpList.get(0).checkContentSame(tmppaper)){
						paperLL.get(j).add(tmppaper);
						mark=true;
					}
				}
				
				if(!mark){
					ArrayList<Paper> paperList=new ArrayList<Paper>();
					paperList.add(tmppaper);
					paperLL.add(paperList);
				}
				
				i++;
				tmppaper=null;
			}
			
			if(i==1){
				return;
			}
			
			for(List<Paper> list:paperLL){
				int size=list.size();
				if(size == 1){
					continue;
				}
				
				keyText.set(String.valueOf(size));
				
				String valueString=null;
				for(Paper entry:list){
					if(valueString==null){
						valueString=entry.getKey();
						continue;
					}
					
					valueString=valueString+"#"+entry.getKey();
				}
				
				valueText.set(valueString);
				if(size>=7 || size<=2){
					System.out.println(".......");
				}
				context.write(keyText, valueText);
			}
		}
	}
	
	public void addRandomRecord() throws IOException{
		BufferedReader reader=new BufferedReader(new InputStreamReader(getDataInputStream()));
		String line=null;
//		line=reader.readLine();
//		String[] datas=line.split("\\|");
//		System.out.println(line);
//		for(String data:datas){
//			System.out.println(">>>>>>>>>>"+data);
//		}
		while((line=reader.readLine())!=null){
			String[] datas=line.split("\\|");
			int r=getRandom();
			for(int i=0;i<r;i++){
				String paperKey=String.valueOf(System.nanoTime());
				//write to paper table
				TableUtil.putRow("paper", paperKey, "information", "year", datas[1]);
				TableUtil.putRow("paper", paperKey, "information", "conference", datas[2]);
				TableUtil.putRow("paper", paperKey, "information", "title", datas[3]);
				TableUtil.putRow("paper", paperKey, "information", "author", datas[4]);
				TableUtil.putRow("paper", paperKey, "information", "typeDate", datas[5]);
				TableUtil.putRow("paper", paperKey, "information", "typeUser", datas[6]);
			}
		}
	}
	
	private  FileInputStream getDataInputStream() throws FileNotFoundException{
		String path="data/randomdata100.txt";
		File file=new File(path);
		FileInputStream in=new FileInputStream(file);
		return in;
	}
	public int getRandom(){
		return (int) Math.floor(Math.random()*4+2);
	}
	
	
	public static void main(String args[]) {
		RepeatDetectJob repeatDetectJob=new RepeatDetectJob();
		try {
//			repeatDetectJob.addRandomRecord();
			repeatDetectJob.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		for(int i=0;i<100;i++){
//			System.out.println(new RepeatDetectJob().getRandom());
//		}
		
	}
}
