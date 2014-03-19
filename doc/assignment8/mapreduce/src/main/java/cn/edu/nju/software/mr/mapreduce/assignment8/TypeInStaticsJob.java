package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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

import cn.edu.nju.software.mr.util.DateUtils;
import cn.edu.nju.software.mr.util.HDFSTest;


public class TypeInStaticsJob {
	public void execute() throws IOException, InterruptedException,	ClassNotFoundException {
		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "TypeInStatic");
		job.setJarByClass(TypeInStaticsJob.class);
		
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("paper", scan, TypeInMapper.class,
				Text.class, MapWritable.class, job);
		job.setReducerClass(TypeInReducer.class);
		job.setNumReduceTasks(1);
		Path path =new Path("hdfs://localhost:9000/result/typeInStatic");
		HDFSTest.deleteFile(path, true);
		FileOutputFormat.setOutputPath(job, path);
		
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}

	public static class TypeInMapper extends TableMapper<Text, MapWritable> {
		
		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
//			String key = new String(value.getRow());
			Text keyText = new Text();
			MapWritable mapWritable = new MapWritable();
			String user = new String(value.getValue("information".getBytes(),"typeUser".getBytes()));
			String dateString=new String(value.getValue("information".getBytes(),"typeDate".getBytes()));
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
	
	public static class TypeInReducer extends Reducer<Text, MapWritable, Text, Text> {
		
		
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
			new TypeInStaticsJob().execute();
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
