package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.IOException;
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


public class PaperSortJob {
	public void execute() throws IOException, InterruptedException,	ClassNotFoundException {
		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "PaperSort");
		job.setJarByClass(PaperSortJob.class);
		
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("comment", scan, PaperSortMapper.class,
				Text.class, Text.class, job);
		job.setSortComparatorClass(KeyComparator.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		job.setReducerClass(PaperSortReducer.class);
		job.setPartitionerClass(PaperSortPartition.class);
		job.setNumReduceTasks(1);
		Path path =new Path("hdfs://localhost:9000/result/PaperSort");
		HDFSTest.deleteFile(path, true);
		FileOutputFormat.setOutputPath(job, path);
		
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}

	public static class KeyComparator extends WritableComparator{
		
		protected KeyComparator() {
			super(Text.class,true);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compare(WritableComparable a, WritableComparable b) {
			// TODO Auto-generated method stub
			Text textA=(Text) a;
			Text textb=(Text) b;
			
			String stringA=new String(textA.getBytes());
			String stringB=new String(textb.getBytes());
			
			String[] as=stringA.split("#");
			String[] bs=stringB.split("#");
			
			int ia=Integer.parseInt(as[0]);
			int ib=Integer.parseInt(bs[0]);
			
			if(ia==ib){
				return as[1].compareTo(bs[1]);
			}
			
			return ib-ia;
		}
		
	}
	
	public static class GroupComparator extends WritableComparator{
		
		protected GroupComparator() {
			super(Text.class,true);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compare(WritableComparable a, WritableComparable b) {
			// TODO Auto-generated method stub
			Text textA=(Text) a;
			Text textb=(Text) b;
			
			String stringA=new String(textA.getBytes());
			String stringB=new String(textb.getBytes());
			
			String[] as=stringA.split("#");
			String[] bs=stringB.split("#");
			
			int ia=Integer.parseInt(as[0]);
			int ib=Integer.parseInt(bs[0]);
			
			return ia-ib;
		}
	}
	public static class PaperSortMapper extends TableMapper<Text, Text> {
		public static int i=0;
		
		
		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
//			String key = new String(value.getRow());
			Text keyText = new Text();
			Text valueText = new Text();
			
			String rowKey=new String(value.getRow());
			i++;
//			System.out.print(i);
			CellScanner cs=value.cellScanner();
			List<String> dateStrings = new ArrayList<String>();
			
			while(cs.advance()){
				Cell cell=cs.current();
				String columnFamily=new String(CellUtil.cloneFamily(cell));
				String columnValue=new String(CellUtil.cloneValue(cell));
				
				if("commentDate".equals(columnFamily)){
					dateStrings.add(columnValue);
				}
			}
			keyText.set(dateStrings.size()+"#"+DateUtils.GetAvgDate(dateStrings));
			valueText.set(rowKey);
			context.write(keyText, valueText);
		}
	}
	
	public static class PaperSortPartition extends Partitioner<Text,Text>{

		@Override
		public int getPartition(Text arg0, Text arg1, int arg2) {
			// TODO Auto-generated method stub
			String[] args=new String(arg0.getBytes()).split("#");
			int num=Integer.parseInt(args[0]);
			
			return num%arg2;
		}
		
	}
	
	public static class PaperSortReducer extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int i=0;
			for(Text text:values){
				i++;
				context.write(key, text);
			}
			System.out.println("reduce group size:"+i);
		}
	}
	
	public static void main(String args[]) {
		try {
			new PaperSortJob().execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
