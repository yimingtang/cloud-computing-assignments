package cn.edu.nju.software.mr.mapreduce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cn.edu.nju.software.mr.util.HDFSTest;
import cn.edu.nju.software.mr.util.TableUtil;

public class Search {
	public Map<String,String> paperMap=new HashMap<String,String>();
	public Map<String,String> authorMap=new HashMap<String,String>();
	
	public void readPaper(){
		try {
			InputStream in = (new HDFSTest()).downloadFile("hdfs://localhost:9000/result/paper/part-r-00000");
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
	    	String s;
	    	while((s=br.readLine())!=null){
//	    		System.out.println(s);
	    		String[] ss=s.split("\t");
//	    		System.out.println(ss.length);
//	    		System.out.println(ss[0]+"="+ss[1]);
	    		paperMap.put(ss[0], ss[1]);
	    	}
	    	System.out.println("paper inverted size:"+paperMap.size());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readAuthor(){
		try {
			InputStream in = (new HDFSTest()).downloadFile("hdfs://localhost:9000/result/author/part-r-00000");
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
	    	String s;
	    	int i=0;
	    	while((s=br.readLine())!=null){
//	    		System.out.println(s);
	    		String[] ss=s.split("\t");
//	    		System.out.println(ss.length);
	    		
	    		authorMap.put(ss[0], ss[1]);
	    		if(i==0){
	    			System.out.println(ss[0]+"="+ss[1]);
	    			System.out.println((String)authorMap.get(ss[0]));
	    		}
	    		i++;
	    	}
	    	System.out.println("author inverted size:"+authorMap.size());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Search search=new Search();
		search.readAuthor();
		search.readPaper();
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please choose type to search(author or paper):");
		String type=scanner.next();
		if("author".equals(type)){
			System.out.println("Please enter the word:");
			String word=scanner.next();
			if(!search.authorMap.containsKey(word)){
				System.out.println("The word is no exits!");
			}else{
				System.out.println("Please enter the key from below:"+(String)search.authorMap.get(type));
				String key=scanner.next();
				try {
					TableUtil.getRow("author", key);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else if("paper".equals(type)){
			System.out.println("Please enter the word:");
			String word=scanner.next();
			if(!search.paperMap.containsKey(word)){
				System.out.println("The word is no exits!");
			}else{
				System.out.println("Please enter the key from below:"+(String)search.paperMap.get(word));
				String key=scanner.next();
				try {
					TableUtil.getRow("paper", key);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("Please enter (author or paper) only!");
		}
		
	}

}
