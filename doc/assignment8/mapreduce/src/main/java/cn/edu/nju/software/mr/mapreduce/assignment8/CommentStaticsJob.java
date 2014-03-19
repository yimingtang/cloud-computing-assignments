package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Map;

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
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.hbase.KeyValue; 
import cn.edu.nju.software.mr.util.DateUtils;
import cn.edu.nju.software.mr.util.HDFSTest;


public class CommentStaticsJob {
	public void execute() throws IOException, InterruptedException,	ClassNotFoundException {
		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "TypeInStatic");
		job.setJarByClass(CommentStaticsJob.class);
		
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("comment", scan, CommentMapper.class,
				Text.class, MapWritable.class, job);
		job.setReducerClass(CommentReducer.class);
		
		job.setNumReduceTasks(1);
		Path path =new Path("hdfs://localhost:9000/result/CommentStatic");
		HDFSTest.deleteFile(path, true);
		FileOutputFormat.setOutputPath(job, path);
		
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}

	public static class CommentMapper extends TableMapper<Text, MapWritable> {
	
		
		
		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
//			String key = new String(value.getRow());
			Text keyText = new Text();
			MapWritable mapWritable = new MapWritable();
			Map<String,String> userMap=new HashMap<String,String>();
			Map<String,String> dateMap=new HashMap<String,String>();
			
//			System.out.println(">>>begin");
//			String rowKey=new String(value.getRow());
			
//			for (KeyValue keyValue : value.raw()) {  
//                System.out.println("列：" + new String(keyValue.getFamily())  
//                        + "====值:" + new String(keyValue.getValue()));  
//            }  
			CellScanner cs=value.cellScanner();
			while(cs.advance()){
//				System.out.println(">>>begin cell");
				Cell cell=cs.current();
				String columnFamily=new String(CellUtil.cloneFamily(cell));
				String columnValue=new String(CellUtil.cloneValue(cell));
				String qualifier=new String(CellUtil.cloneQualifier(cell));
				
				if("commentDate".equals(columnFamily)){
					dateMap.put(qualifier, columnValue);
				}
				
				if("commentUser".equals(columnFamily)){
					userMap.put(qualifier, columnValue);
				}
//				System.out.println("columnFamily:"+columnFamily);
//				System.out.println("qualifier:"+qualifier);
//				System.out.println("columnValue:"+columnValue);
//				
//				System.out.println(">>>end cell");
			}
//			System.out.println(">>>end");
			
			Set<Entry<String,String>> entries=userMap.entrySet();
			for(Entry<String,String> entry:entries){
				String mapKey=entry.getKey();
				String user = entry.getValue();
				String dateString=dateMap.get(mapKey);
				keyText.set(user);
				mapWritable.put(new Text(DateUtils.AllString), new IntWritable(1));
				
				if(DateUtils.IsInOneYear(dateString)){
					mapWritable.put(new Text(DateUtils.OneYearString), new IntWritable(1));
					
					if(DateUtils.IsInHalfYear(dateString)){
						mapWritable.put(new Text(DateUtils.HalfYearString), new IntWritable(1));
						
						if(DateUtils.IsInOneMonth(dateString)){
							mapWritable.put(new Text(DateUtils.OneMonthString), new IntWritable(1));
							
							if(DateUtils.IsInOneWeek(dateString)){
								mapWritable.put(new Text(DateUtils.OneWeekString), new IntWritable(1));
							}
						}
					}
				}
				context.write(keyText, mapWritable);
			}
		}
	}
	
	public static class CommentReducer extends Reducer<Text, MapWritable, Text, Text> {
		
		
		public void reduce(Text key, Iterable<MapWritable> values, Context context)
				throws IOException, InterruptedException {
			MapWritable valueMap=new MapWritable();
			Text valueText=new Text();
			
			for (MapWritable map : values) {
				Set<Entry<Writable, Writable>> entries = map.entrySet();
				
				for(Entry<Writable,Writable> entry :entries){
					Text keyText = (Text)entry.getKey();
					IntWritable value=(IntWritable)entry.getValue();
					
					if(!valueMap.containsKey(keyText)){
						valueMap.put(keyText, value);
					}else{
						IntWritable oldValue=(IntWritable)valueMap.get(keyText);
						int valueTmp=oldValue.get()+value.get();
						
						oldValue.set(valueTmp);
						valueMap.put(keyText, oldValue);
					}
				}
			}
			
			String valueString=null;
			Set<Entry<Writable, Writable>> entries = valueMap.entrySet();
			for(Entry<Writable,Writable> entry :entries){
				Text keyText = (Text)entry.getKey();
				IntWritable value=(IntWritable)entry.getValue();
				
				if(valueString==null){
					valueString=new String(keyText.getBytes())+":"+value.get();
				}else{
					valueString=valueString +","+new String(keyText.getBytes())+":"+value.get();
				}
			}
			valueText.set(valueString);
			context.write(key, valueText);
		}
	}
	
	public static void main(String args[]) {
		try {
			new CommentStaticsJob().execute();
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
