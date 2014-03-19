package cn.edu.nju.software.mr;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import java.util.*;

public class MapTest {
	public static void main(String[] args){
		Map<Text,IntWritable> map=new HashMap<Text,IntWritable>();
		Text text1=new Text("ceshi");
		IntWritable value1=new IntWritable(2);
		map.put(text1, value1);
		
		Text text2=new Text("ceshi");
		System.out.println(map.containsKey(text2));
		IntWritable value2=map.get(text2);
		value2.set(3);
		System.out.println(map.get(text2).get());
	}
}
