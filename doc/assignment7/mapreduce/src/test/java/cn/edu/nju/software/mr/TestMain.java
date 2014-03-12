package cn.edu.nju.software.mr;

public class TestMain {

	public static void main(String args[]){
		String s="oehwoth wqo|safas|RDTG";
		String[] ss=s.split("\\|");
		for(int i=0;i<ss.length;i++){
			System.out.println(">>>"+ss[i]);
		}
	}
}
