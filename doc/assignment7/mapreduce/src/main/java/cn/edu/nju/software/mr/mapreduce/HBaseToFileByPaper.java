package cn.edu.nju.software.mr.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;

public class HBaseToFileByPaper {
	public void execute() throws IOException, InterruptedException,
			ClassNotFoundException {
		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "Paper InvertedIndex");
		job.setJarByClass(HBaseToFileByPaper.class);

		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("paper", scan, PaperMapper.class,
				Text.class, Text.class, job);
		job.setReducerClass(PaperReducer.class);
		job.setNumReduceTasks(1);
		FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/result/paper"));

		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}

	}

	public static class PaperMapper extends TableMapper<Text, Text> {
		public static final byte[] CF = "information".getBytes();
		public static final byte[] QUARIFIER = "title".getBytes();

		private Text keyText = new Text();
		private Text valueText = new Text();

		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
			String title = new String(value.getValue(CF, QUARIFIER));
			valueText.set(value.getRow());

			String[] words = title.split(" ");
			for (String word : words) {
				keyText.set(word);
				context.write(keyText, valueText);
			}
		}
	}

	public static class PaperReducer extends
			Reducer<Text, Text, Text, Text> {
		private Text valueText=new Text();
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			String all = null;
			for (Text val : values) {
				if(all == null){
					all=new String(val.getBytes());
					continue;
				}
				all = all + "#" + new String(val.getBytes());
			}
			System.out.println("....................");
			valueText.set(all);
			context.write(key, valueText);
		}
	}

	public static void main(String args[]) {
		try {
			new HBaseToFileByPaper().execute();
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
