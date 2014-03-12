package cn.edu.nju.software.mr.util;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;


/**
 * Hello world!
 *
 */
public class HDFSTest 
{
	private Configuration conf=new Configuration();
	private Path path=new Path("hdfs://localhost:9000/");
	private FileSystem fs;
	
	public HDFSTest(){
		try {
			fs=FileSystem.get(path.toUri(), conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void main( String[] args ) throws IOException
    {
    	HDFSTest ht=new HDFSTest();
//    	ht.copyToHdfs();
//    	ht.mkdirs();
//    	ht.createFile();
//    	ht.downloadFile();
//    	ht.printHDFS();
    }
    //复制文件
    public void copyToHdfs() throws IOException{
    	Path src=new Path("/usr/hadooptest/site.xml");
    	Path dst=new Path("/input/site.xml");
    	fs.copyFromLocalFile(src, dst);
    }
    
    //新建目录
    public void mkdirs() throws IllegalArgumentException, IOException{
    	boolean flag=fs.mkdirs(new Path(path.toUri()+"test"));
    }
    
    //新建文件
    public void createFile() throws IllegalArgumentException, IOException{
    	File file=new File("/usr/hadooptest/site.xml");
    	InputStream in=new BufferedInputStream(new FileInputStream(file));
    	OutputStream out=fs.create(new Path("/test/new.xml"),new Progressable(){

			@Override
			public void progress() {
				// TODO Auto-generated method stub
				System.out.println(" *");
			}
    		
    	});
    	
    	IOUtils.copyBytes(in,out,4096,true);
    }
    
    //文件下载
    public void downloadFile() throws IllegalArgumentException, IOException{
    	String down_path="/test/new.xml";
    	FSDataInputStream fin=fs.open(new Path(down_path));
    	BufferedReader br=new BufferedReader(new InputStreamReader(fin));
    	String s;
    	while((s=br.readLine())!=null){
    		System.out.println(s);
    	}
    }
    
    public InputStream downloadFile(String path) throws IllegalArgumentException, IOException{
    	FSDataInputStream fin=fs.open(new Path(path));
    	return fin;
    }
    
    //文件删除delete(Path f, Boolean recursive)
    
    
    
    public void printHDFS() throws IOException{
    	Path path=new Path("/");
    	int dep=1;
    	printHDFS(path,dep);
    }
    
    public void printHDFS(Path path,int dep) throws FileNotFoundException, IOException{
    	FileStatus fileList[] =fs.listStatus(path);
    	int len =fileList.length;
    	for(int i=0;i<len;i++){
    		for(int d=0;d<dep;d++){
    			System.out.print(">>>>>");
    		}
    		System.out.print("file name:"+fileList[i].getPath().getName());//文件名
    		System.out.println(" size:"+fileList[i].getLen());//文件大小
    		
    		if(fileList[i].isDirectory()){
    			int tmpdep=dep+1;
    			printHDFS(fileList[i].getPath(),tmpdep);
    		}
    	}
    }
}
