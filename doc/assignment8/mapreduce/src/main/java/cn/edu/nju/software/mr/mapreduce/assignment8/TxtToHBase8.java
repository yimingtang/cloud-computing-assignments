package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;

import cn.edu.nju.software.mr.util.TableUtil;


public class TxtToHBase8 {
	public static void createTables(){
		
		String paperTableName="paper";
		String[] paperTableCF={"information"};
		try {
			TableUtil.createTable(paperTableName, paperTableCF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String authorTableName="author";
		String[] authorTableCF={"papername"};//papername:paperkey=papername
		try {
			TableUtil.createTable(authorTableName, authorTableCF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String conferenceTableName="conference";
		String[] conferenceTableCF={"papername"};//papername:paperkey=papername
		try {
			TableUtil.createTable(conferenceTableName, conferenceTableCF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String commentTableName="comment";
		String[] commentTableCF={"commentDate","commentUser"};//papername:paperkey=papername
		try {
			TableUtil.createTable(commentTableName, commentTableCF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deleteTables(){
		try {
			TableUtil.deleteTable("paper");
			TableUtil.deleteTable("author");
			TableUtil.deleteTable("conference");
			TableUtil.deleteTable("comment");
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readTxtDataToHBase() throws IOException{
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
			String paperKey=String.valueOf(System.nanoTime());
//			System.out.println("line>>>>"+line);
//			for(String data:datas){
//				System.out.println(">>>>>>>>>>"+data);
//			}
			//write to paper table
			TableUtil.putRow("paper", paperKey, "information", "year", datas[1]);
			TableUtil.putRow("paper", paperKey, "information", "conference", datas[2]);
			TableUtil.putRow("paper", paperKey, "information", "title", datas[3]);
			TableUtil.putRow("paper", paperKey, "information", "author", datas[4]);
			TableUtil.putRow("paper", paperKey, "information", "typeDate", datas[5]);
			TableUtil.putRow("paper", paperKey, "information", "typeUser", datas[6]);
			
			//write to author table
			TableUtil.putRow("author", datas[4], "papername", paperKey, datas[3]);
			
			//write to conference table
			TableUtil.putRow("conference", datas[2], "papername", paperKey, datas[3]);
			
			//write to comment table
			String commentString=datas[7];
			String[] comments=commentString.split(",");
			for(String comment:comments){
				String[] commentInfo=comment.split("~");
				String commentKey=paperKey+commentInfo[1]+commentInfo[0]+String.valueOf(System.nanoTime());
				commentKey=String.valueOf(commentKey.hashCode());
				TableUtil.putRow("comment", paperKey, "commentUser", commentKey, commentInfo[0]);
				TableUtil.putRow("comment", paperKey, "commentDate", commentKey, commentInfo[1]);
			}
		}
	}
	
	private static FileInputStream getDataInputStream() throws FileNotFoundException{
		String path="data/data8.txt";
		File file=new File(path);
		FileInputStream in=new FileInputStream(file);
		return in;
	}
	
	public static void main(String[] args){
		TxtToHBase8.createTables();
		try {
			long beginTime =System.currentTimeMillis();
			TxtToHBase8.readTxtDataToHBase();
			long endTime=System.currentTimeMillis();
			System.out.println("Time:"+(endTime-beginTime)+"ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		TxtToHBase8.deleteTables();
	}
}
