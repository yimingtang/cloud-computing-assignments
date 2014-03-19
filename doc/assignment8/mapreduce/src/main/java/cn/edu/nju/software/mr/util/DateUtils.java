package cn.edu.nju.software.mr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 7*24*60*60*1000
	 */
	public static String OneWeekString="OneWeek";
	public static String OneMonthString="OneMonth";
	public static String HalfYearString="HalfYear";
	public static String OneYearString="OneYear";
	public static String AllString="All";
	
	private static long OneWeek=Long.parseLong("604800000");
	/**
	 * 30*24*60*60*1000
	 */
	private static long OneMonth=Long.parseLong("2592000000");
	/**
	 * 183*24*60*60*1000
	 */
	private static long HalfYear=Long.parseLong("15811200000");
	/**
	 * 365*24*60*60*1000
	 */
	private static long OneYear=Long.parseLong("31536000000");
	
	public static Date Parse(String dateString){
		Date result=null;
		try {
			result= format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String Format(Date date){
		return format.format(date);
	}
	
	
	public static boolean IsInOneWeek(Date date){
		return IsDateIn(date,OneWeek);
	}
	
	public static boolean IsInOneWeek(String dateString){
		return IsInOneWeek(Parse(dateString));
	}
	
	
	public static boolean IsInOneMonth(Date date){
		return IsDateIn(date,OneMonth);
	}
	
	public static boolean IsInOneMonth(String dateString){
		return IsInOneMonth(Parse(dateString));
	}
	
	public static boolean IsInHalfYear(Date date){
		return IsDateIn(date,HalfYear);
	}
	
	public static boolean IsInHalfYear(String dateString){
		return IsInHalfYear(Parse(dateString));
	}
	
	public static boolean IsInOneYear(Date date){
		return IsDateIn(date,OneYear);
	}
	
	public static boolean IsInOneYear(String dateString){
		return IsInOneYear(Parse(dateString));
	}
	
	private static boolean IsDateIn(Date date ,long time){
		Calendar cal=Calendar.getInstance();
		long cur=GetFormatTime(cal.getTime());
		long dateTime=GetFormatTime(date);
		
		long diff=cur-dateTime;
		if(diff<=time){
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * @param date
	 * @return 把时间都设为0
	 */
	public static Date FormatDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.HOUR,0);
		return calendar.getTime();
	}
	
	/**
	 * 只返回日期的time值，其余字段均为0
	 */
	public static long GetFormatTime(Date date){
		return FormatDate(date).getTime();
	}
	
	public static String GetAvgDate(List<String> dateStrings){
		long all=0;
		int l=dateStrings.size();
		for(String dateString:dateStrings){
			all=all+GetFormatTime(Parse(dateString));
		}
		
		long avg=all/l;
	
		Date date=new Date(avg);
		return format.format(date);
	}
	public static void main(String[] args){
		Date date=DateUtils.Parse("2014-3-5");
		System.out.println(DateUtils.IsInOneWeek(date));
		System.out.println(DateUtils.IsInOneMonth(date));
		System.out.println(DateUtils.IsInHalfYear(date));
		System.out.println(DateUtils.IsInOneYear(date));
	}
}
