package rml.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String TIME_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	public static final String TIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss SSS";

	public static final String TIME_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
	
	public static final String TIME_FROMAT_DAY = "yyyy-MM-dd";

	public static final String TIME_FORMAT_MONTH = "yyyy-MM";

	public static Date strToDate(String dateStr) {
		return strToDate(dateStr, TIME_FORMAT_FULL);
	}
	
	public static Date strToDate(String dateStr, String format) {
		if (null != dateStr && !"".equals(dateStr.trim())) {
			try {
				SimpleDateFormat df = null;
				Date date = null;
				df = new SimpleDateFormat(format);

				try {
					date = df.parse(dateStr);
				} catch (ParseException var5) {
					;
				}
				return date;
			} catch (Exception e) {
				LogFactory.getLog(DateUtil.class).warn(e);
				return null;
			}
		}
		return null;
	}
	
	public static Date longToDate(Long dateLong, String format){
		if (null != dateLong) {
			try {
				return strToDate(formatDate(new Date(dateLong),format),format );
			} catch (Exception e) {
				LogFactory.getLog(DateUtil.class).warn(e);
				return null;
			}
		}
		return null;
	}
	
	
	public static String longToStr(Long dateLong , String format){
		return formatDate(longToDate(dateLong, format),format);
	}
	
	
	public static String formatDate(Date date) {
		return formatDate(date, TIME_FORMAT_FULL);
	}
	
	public static String formatDate(Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
    }

	/**
	 * 获取输入日期的前、后N天
	 * <br/> 输入正数，返回N天之后的日期；
	 * <br/> 输入负数，返回N天之前的日期
	 *
	 * @param currentDate 输入日期
	 * @param day         加减的天数
	 * @return
	 */
	public static Date addDay(Date currentDate, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	/**
	 * 获取输入日期的前、后N年
	 * <br/> 输入正数，返回N年之后的日期；
	 * <br/> 输入负数，返回N年之前的日期
	 *
	 * @param currentDate 输入日期
	 * @param year         加减的年数
	 * @return
	 */
	public static Date addYear(Date currentDate, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.YEAR, year);
		return c.getTime();
	}

	public static int period(Long beginTime, Long endTime){
		 java.util.Calendar calst = java.util.Calendar.getInstance();
         java.util.Calendar caled = java.util.Calendar.getInstance();
         calst.setTime(new Date(beginTime));
          caled.setTime(new Date(endTime));
          //设置时间为0时
          calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
          calst.set(java.util.Calendar.MINUTE, 0);
          calst.set(java.util.Calendar.SECOND, 0);
          caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
          caled.set(java.util.Calendar.MINUTE, 0);
          caled.set(java.util.Calendar.SECOND, 0);
         //得到两个日期相差的天数   
          int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                 .getTime().getTime() / 1000)) / 3600 / 24;   
          
         return days;  
	}
	
	public static Date getDate(Date date, int hour, int minute, int second){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		Date d = c.getTime();
        return d;  
	}
	
	
	public static void main(String[] args) {
		Long a = strToDate("2016-7-1 00:00:00",TIME_FORMAT_FULL).getTime();
		Long b = strToDate("2016-7-2 23:59:59",TIME_FORMAT_FULL).getTime();
	    System.out.println(period(a,b));

	}
}
