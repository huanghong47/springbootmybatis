package cn.hhfarcry.springbootmybatis.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 09:07
 */
public class DateTimeUtils {
    private static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_FORMAT_01 = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String DATETIME_FORMAT_02 = "yyyyMMddHHmmss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATETIME_FORMAT_03 = "yyyy-MM-dd";
    /**
     * HH:mm:ss
     */
    public static final String DATETIME_FORMAT_04 = "HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String DATETIME_FORMAT_05 = "yyyy-MM-dd HH:mm";
    /**
     * yyyyMMddHHmm
     */
    public static final String DATETIME_FORMAT_06 = "yyyyMMddHHmm";
    /**
     * yyyyMMdd
     */
    public static final String DATETIME_FORMAT_07 = "yyyyMMdd";

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * 时间转换字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String format){
        if (null == date || ParamUtils.isBlank(format)){
            return null;
        }
        try{
            SimpleDateFormat sdf = getSdf(format);
            return sdf.format(date);
        }catch (Exception e) {
            logger.error("{} --> date2String error",e);
            return null;
        }
    }


    /**
     * 字符串转时间
     * @param string
     * @param format
     * @return
     */
    public static Date string2Date(String string, String format){
        if(ParamUtils.isBlank(string) || ParamUtils.isBlank(format)){
            return null;
        }
        try {
            SimpleDateFormat sdf = getSdf(format);
            return sdf.parse(string);
        } catch (Exception e) {
            logger.error("{} --> date2String error", e);
            return null;
        }
    }

    /**
     * 时间格式转换
     * @param string
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String toOtherFormat(String string, String fromFormat, String toFormat){
        try {
            SimpleDateFormat sdf = getSdf(fromFormat);
            sdf.applyPattern(fromFormat);
            Date date = sdf.parse(string);
            sdf.applyPattern(toFormat);
            return sdf.format(date);
        } catch (Exception e) {
            //log.error("DateTimeUtil --> 2OtherFormat error", e);
            return null;
        }
    }

    /**
     * 判断是否是日期或时间
     * @param
     * @return
     */
    public static boolean isDateTime(String dateString, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        boolean dateflag = true;
        try{
            format.parse(dateString);
        } catch (ParseException e){
            dateflag = false;
        }
        return dateflag;
    }

    /**
     * 获取当月第一天时间 00:00:00
     * @return
     */
    public static Date getFirstDayOfCurrMonth(){
        return getFirstDayOfMonth(null);
    }

    /**
     * 获取某月第一天时间 00:00:00
     * @return
     */
    public static Date getFirstDayOfMonth(Date date){
        Calendar cal_1 = Calendar.getInstance();
        if(null != date)
            cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        cal_1.set(Calendar.HOUR_OF_DAY, 0);
        cal_1.set(Calendar.MINUTE, 0);
        cal_1.set(Calendar.SECOND, 0);
        return cal_1.getTime();
    }



    /**
     * 获取月天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当天开始时间
     * 00:00:00 0
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天最后时间
     * 23:23:59 999
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 按年获取时间
     * @param date
     * @param yearPass
     * @return
     */
    public static Date getDateByYear(Date date, int yearPass) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(null == date ? new Date() : date);
        calendar.add(Calendar.YEAR, yearPass);

        return calendar.getTime();
    }

    /**
     * 按天获取时间
     * @param date
     * @param dayPass
     * @return
     */
    public static Date getDateByDay(Date date, int dayPass) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(null == date ? new Date() : date);
        calendar.add(Calendar.DAY_OF_MONTH, dayPass);

        return calendar.getTime();
    }


    /**
     * 获取两个日期间的间隔天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDays(Date startDate, Date endDate){
        if(startDate == null || endDate == null){
            return 0;
        }
        return (int)((endDate.getTime() - startDate.getTime())/86400000);
    }
    /**
     * 获取系统当前时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 获取当前日期是星期几<br>
     * @param date
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0)
            w = 7;
        return w;
    }

    /**
     * 获取当前日期是几号
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 毫秒时间
     * Long类型时间转换成视频时长
     */
    public static String longTime2Str(Long time){
        if(time == null){
            return null;
        }else{
            long hour = time/(60*60*1000);
            long minute = (time - hour*60*60*1000)/(60*1000);
            long second = (time - hour*60*60*1000 - minute*60*1000)/1000;
            return (hour==0?"00":(hour>=10?hour:("0"+hour)))+":"+(minute==0?"00":(minute>=10?minute:("0"+minute)))+":"+(second==0?"00":(second>=10?second:("0"+second)));
        }
    }

    /** 锁对象 */
    private static final Object lockObj = new Object();

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new ConcurrentHashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }
}
