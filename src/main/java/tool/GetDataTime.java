package tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDataTime {
	/*	public static void main(String[] args) {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	
	}
*/
	
	


		public static String getDataTime(int n) {
			Date date = new Date();// 获得当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    return 	df.format(getNextDay(date,n));// 返回下n天日期

		}

		public static String getNextDay(Date date ,int n) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, +n);//返回下一天的日期
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = calendar.getTime();
			
			
			return simpleDateFormat.format(date);
		}
		
		public static String getCurrentTime(){
			Date date =new Date();
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
			String currentTime=simpleDateFormat.format(date);
			
			return currentTime;
		}
		public static String getCurrentFileTime(){
			Date date =new Date();
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyMMddHHmmss");
			String currentTime=simpleDateFormat.format(date);
			
			return currentTime;
		}
		public static String getNowDateTime(){
			Date date =new Date();
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyMMdd");
			String currentTime=simpleDateFormat.format(date);
			
			return currentTime;
		}
		public static String getDateTime(){
			Date date =new Date();
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyy-MM-dd");
			String currentTime=simpleDateFormat.format(date);
			
			return currentTime;
		}
	
}
