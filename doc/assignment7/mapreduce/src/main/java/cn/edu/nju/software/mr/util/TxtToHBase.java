package cn.edu.nju.software.mr.util;

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


public class TxtToHBase {
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
	}
	public static void deleteTables(){
		try {
			TableUtil.deleteTable("paper");
			TableUtil.deleteTable("author");
			TableUtil.deleteTable("conference");
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
		while((line=reader.readLine())!=null){
			String[] datas=line.split("\\|");
			String paperKey=String.valueOf(System.nanoTime());
			System.out.println(line);
//			for(String data:datas){
//				System.out.println(">>>>>>>>>>"+data);
//			}
			//write to paper table
			TableUtil.putRow("paper", paperKey, "information", "year", datas[1]);
			TableUtil.putRow("paper", paperKey, "information", "conference", datas[2]);
			TableUtil.putRow("paper", paperKey, "information", "title", datas[3]);
			TableUtil.putRow("paper", paperKey, "information", "author", datas[4]);
			
			//write to author table
			TableUtil.putRow("author", datas[4], "papername", paperKey, datas[3]);
			
			//write to conference table
			TableUtil.putRow("conference", datas[2], "papername", paperKey, datas[3]);
		}
	}
	
	private static FileInputStream getDataInputStream() throws FileNotFoundException{
		String path="data/data.txt";
		File file=new File(path);
		FileInputStream in=new FileInputStream(file);
		return in;
	}
	
	public static void main(String[] args){
		TxtToHBase.createTables();
		try {
			TxtToHBase.readTxtDataToHBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		TxtToHBase.deleteTables();
	}
}
