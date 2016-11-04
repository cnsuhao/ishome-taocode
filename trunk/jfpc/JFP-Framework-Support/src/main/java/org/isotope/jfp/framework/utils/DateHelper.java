package org.isotope.jfp.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 日期格式化
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class DateHelper {
	/**
     * 返回日期格式的字符串
     * @param dateStr
     * @param format
     * @return
     */
    public static String formatDateBySpecified(String dateStr,String format){        
    	HashMap<String, String> dateRegFormat = new HashMap<String, String>();   
    	//\w{3}\s\w{3}\s\d{2}\s\d{2}:\d{2}:\d{2}\s+\w+\s+\d{4}
    	dateRegFormat.put("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s+\\w+\\s+\\d{4}", "EEE MMM dd HH:mm:ss Z yyyy");
    	dateRegFormat.put("\\d{4}年\\d{1,2}月\\d{1,2}日", "yyyy年MM月dd");
    	dateRegFormat.put("\\d{4}-\\d{1,2}-\\d{1,2}", "yyyy-MM-dd");
    	dateRegFormat.put("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{6}", "yyyy-MM-dd"); //2007-01-29 00:00:00.000000
    	//Jul 20, 2015 12:00:00 AM
    	dateRegFormat.put("\\w{3}\\s\\w{1,2},\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\s\\w{2}", "MMM dd, yyyy hh:mm:ss a");
    	//1999-12-08T00:00:00
    	dateRegFormat.put("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2}", "yyyy-MM-dd'T'HH:mm:ss");//
    	//20151112
    	dateRegFormat.put("\\d{8}","yyyyMMdd");
    	DateFormat ress_form = new SimpleDateFormat(format);
    	String strSuccess= null;   
    	DateFormat oldForm = null;
    	try {       
    		for (String key : dateRegFormat.keySet()) {      
    			if (Pattern.compile(key).matcher(dateStr).matches()) {  
    					if("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s+\\w+\\s+\\d{4}".equals(key)){
    						oldForm = new SimpleDateFormat(dateRegFormat.get(key),Locale.UK);
    					}else if("\\w{3}\\s\\w{1,2},\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\s\\w{2}".equals(key)){
    						oldForm = new SimpleDateFormat(dateRegFormat.get(key),Locale.ENGLISH);
    					}else{
    						oldForm = new SimpleDateFormat(dateRegFormat.get(key));
    					}
    					strSuccess = ress_form.format(oldForm.parse(dateStr));
    				break;         
    				}      
    			} 
    	}catch(Exception e){
    		return null;
    	}
		return strSuccess;  
    }
    /**
     * 系统时间戳
     * 
     * @return
     */
    public static String currentTimestamp() {
        return "" + System.currentTimeMillis();
    }

    /**
     * yyyyMMddHHmmss
     * 
     * @return
     */
    public static String currentTimeMillis0() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * yyMMddHHmmss
     * 
     * @return
     */
    public static String currentTimeMillis1() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * yyyy/MM/dd HH:mm:ss
     * 
     * @return
     */
    public static String currentTimeMillis2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * yy/MM/dd HH:mm:ss
     * 
     * @return
     */
    public static String currentTimeMillis3() {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * yyyy/MM/dd HH:mm:ss.sss
     * 
     * @return
     */
    public static String currentTimeMillis4() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
        return format.format(new Date());
    }

    /**
     * yy/MM/dd HH:mm:ss.sss
     * 
     * @return
     */
    public static String currentTimeMillis5() {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss.sss");
        return format.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String currentTimeMillisCN1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * yy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String currentTimeMillisCN2() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * yyyy-MM-dd
     * 
     * @return
     */
    public static String currentTimeMillisCN3() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * yy-MM-dd
     * 
     * @return
     */
    public static String currentTimeMillisCN4() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        return format.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss.sss
     * 
     * @return
     */
    public static String currentTimeMillisCN5() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        return format.format(new Date());
    }

    /**
     * yy-MM-dd HH:mm:ss.sss
     * 
     * @return
     */
    public static String currentTimeMillisCN6() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss.sss");
        return format.format(new Date());
    }

    /**
     * yyMMdd
     * 
     * @return
     */
    public static String currentDate1() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        return format.format(new Date());
    }

    /**
     * yy/MM/dd
     * 
     * @return
     */
    public static String currentDate2() {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
        return format.format(new Date());
    }

    /**
     * yyyyMMdd
     * 
     * @return
     */
    public static String currentDate3() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    /**
     * yyyy/MM/dd
     * 
     * @return
     */
    public static String currentDate4() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date());
    }

    /**
     * HHmmss
     * 
     * @return
     */
    public static String currentTime1() {
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        return format.format(new Date());
    }

    /**
     * HH:mm:ss
     * 
     * @return
     */
    public static String currentTime2() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * HH:mm:ss
     * 
     * @return
     */
    public static String customTime(String formatType) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        return format.format(new Date());
    }

    public static String customTime(Date date, String formatType) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        return format.format(date);
    }

    /**
     * 日期转换<br>
     * 20151111日<br>
     * 2015.6.19<br>
     * 2015/6/19 <br>
     * 2015-6-19 <br>
     * 2015年11月11日<br>
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getDate(String date) throws Exception {
        Calendar c = Calendar.getInstance();
        try{
	        if (date.length() == 8) {
	            c.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
	        } else {
	            date = date.replaceAll("[\\.\\-\\年\\月]", "/").replace("日", "");
	            String[] d = date.split("/");
	            c.set(Integer.parseInt(d[0]), Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]));
	        }
        }catch(Exception e){
        	return null;
        }
        return c.getTime();
    }

    public static String getDate(String date, String formatType) throws Exception {
        return customTime(getDate(date), formatType);
    }
    
    public static int getDaysBetween(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
     }

    public static void main(String[] args) throws Exception {
//        //System.out.println(getDate("20150619", "yyyy/MM/dd"));
//        //System.out.println(getDate("2015/6/19", "yyyy/MM/dd"));
//        //System.out.println(getDate("2015-6-19", "yyyy/MM/dd"));
//        //System.out.println(getDate("2015年6月19日", "yyyy/MM/dd"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print( format.format(new Date(1464587877000L)));
    }
}
