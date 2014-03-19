package cn.edu.nju.software.mr.mapreduce.assignment8;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


public class Paper implements Writable{
	private String key;
	private String title;
	private String conference;
	private String year;
	private String author;
	private String typeDate;
	private String typeUser;
	
	public Paper(){
		
	}
	
	public Paper(Paper p){
		key=p.getKey();
		title=p.getTitle();
		conference=p.getConference();
		year=p.getYear();
		author=p.getAuthor();
		typeDate=p.getTypeDate();
		typeUser=p.getTypeUser();
	}
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		String in=arg0.readLine();
		String[] data=in.split("#");
		key=data[0];
		title=data[1];
		conference=data[2];
		year=data[3];
		author=data[4];
		typeDate=data[5];
		typeUser=data[6];
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		String out=key+"#"+title+"#"+conference+"#"+year+"#"+author+"#"+typeDate+"#"+typeUser+"\n";
		arg0.writeBytes(out);
	}

	
	public boolean checkContentSame(Paper paper){
		if(title.equals(paper.getTitle()) && conference.equals(paper.getConference()) &&year.equals(paper.getYear()) &&author.equals(paper.getAuthor()) &&typeDate.equals(paper.getTypeDate()) && typeUser.equals(paper.getTypeUser())){
			return true;
		}
		
		return false;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTypeDate() {
		return typeDate;
	}

	public void setTypeDate(String typeDate) {
		this.typeDate = typeDate;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
