package cn.edu.nju.software.mr.util;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;  
import org.apache.hadoop.hbase.client.ResultScanner; 
import org.apache.hadoop.hbase.KeyValue; 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class TableUtil {
	public static Configuration conf = null;
	private static HConnection connection = null;
	static{
		conf=HBaseConfiguration.create();
		try {
			connection = HConnectionManager.createConnection(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTable(String tablename, String[] familyname)
			throws IOException {
		HBaseAdmin hAdmin = new HBaseAdmin(conf);

		if (hAdmin.tableExists(tablename)) {
			System.out.println("表已经存在:"+tablename);
		}else{
			HTableDescriptor tableDes=new HTableDescriptor(TableName.valueOf(tablename));
			for(String family:familyname){
				tableDes.addFamily(new HColumnDescriptor(family));
			}
			hAdmin.createTable(tableDes);
			System.out.println("创建表成功:"+tablename);
		}
	}
	
	public static void deleteTable(String tablename) throws MasterNotRunningException, ZooKeeperConnectionException, IOException{
		HBaseAdmin hAdmin = new HBaseAdmin(conf);
		if (!hAdmin.tableExists(tablename)) {
			System.out.println("表bu存在:"+tablename);
		}else{
			hAdmin.disableTable(tablename);
			hAdmin.deleteTable(tablename);
			System.out.println("shanchu表成功:"+tablename);
		}
	}
	
	public static void putRow(String tablename,String rowkey,String columnFamily,String column,String value) throws IOException{
		
		HTableInterface hTable=connection.getTable(tablename);
		Put put=new Put(rowkey.getBytes());
		put.add(columnFamily.getBytes(), column.getBytes(), value.getBytes());
		hTable.put(put);
	}
	
	public static void getRow(String tablename, String row) throws IOException{
		HTableInterface hTable=connection.getTable(tablename);
	    Get get = new Get(Bytes.toBytes(row));  
	    Result result = hTable.get(get);  
        for (KeyValue rowKV : result.raw()) {  
            System.out.print("Row Name: " + new String(rowKV.getRow()) + " ");  
            System.out.print("Timestamp: " + rowKV.getTimestamp() + " ");  
            System.out.print("column Family: " + new String(rowKV.getFamily()) + " ");  
            System.out.print("Row Name:  " + new String(rowKV.getQualifier()) + " ");  
            System.out.println("Value: " + new String(rowKV.getValue()) + " ");  
        } 
	}
	
	public static void main(String args[]){
		String[] testFamily={"cf"};
		try {
			TableUtil.createTable("test", testFamily);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
