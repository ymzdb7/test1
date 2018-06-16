package com.winhands.base.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

	private static Calendar calendar = Calendar.getInstance();
	public static SimpleDateFormat dateFormat = new SimpleDateFormat();
	
	/**
     * 返回当前系统时间 
     *
     * @return 系统时间
     */
    public static Date getDate() {
        return new Date();
    }


	/**
	 * 根据样式获取当前日期
	 * @param format
	 * @return
	 */
	public static String getDateStr(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
     * 返回当前系统日期、时间
     *
     * @param format 返回日期的格式
     * @return 系统日期、时间
     */
    public static Date getDate(String format) {
        return parse(getLocalDate(format));
    }
    
    /**
     * 获取指定格式的系统时间字符串
     *
     * @param dateFormat 日期格式
     * @return 指定格式的系统时间
     */
    public static String getLocalDate(String dateFormat) {
        return format(getDate(), dateFormat);
    }
    
	/**
	 *
	 * 方法描述
	 *
	 * @param strDate
	 *            时间的字符串
	 * @param pattern
	 *            时间格式
	 * @param days
	 *            天数
	 * @return 指定天数加上指定天数的时间的字符串格式
	 */
	public static String AddDay(String strDate,String pattern,int days){
		if(strDate == null || "".equals(strDate) || pattern == null || "".equals(pattern) || strDate.length() < pattern.length()){
			return null;
		}
		String resultDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date = dateFormat.parse(strDate.substring(0,pattern.length()));
			Calendar cale = Calendar.getInstance();
			cale.setTime(date);
			// 指定时间内加上指定天数
			cale.add(Calendar.DAY_OF_MONTH, days);
			date = cale.getTime();
			// 结果时间的字符串格式
			resultDate = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}
	/**
	 * 
	 * <b>Summary: </b>
	 *    backwordDay 减少天数
	 * Date
	 * @param d
	 * @param days
	 * @return 
	 * backwordDay
	 * Jan 3, 2014
	 */
	public static Date backwordDay(Date d, int days){
        calendar.setTime(d);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }
	//指定日期新增天数
    public static Date forwordDay(Date d, int days)
    {
        calendar.setTime(d);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    /**
     * 
     * <b>Summary: </b>
     *    backwordMonth 减少指定月份
     * Date
     * @param d
     * @param months
     * @return 
     * backwordMonth
     * Jan 3, 2014
     */
    public static Date backwordMonth(Date d, int months)
    {
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, -months);
        return calendar.getTime();
    }
    /**
     * 
     * <b>Summary: </b>
     *    forwordMonth  增加指定月份
     * Date
     * @param d
     * @param months
     * @return 
     * forwordMonth
     * Jan 3, 2014
     */
    public static Date forwordMonth(Date d, int months) {
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
    /**
     * 
     * <b>Summary: </b>
     *    backwordYear 减少年
     * Date
     * @param d
     * @param years
     * @return 
     * backwordYear
     * Jan 3, 2014
     */
    public static Date backwordYear(Date d, int years)
    {
        calendar.setTime(d);
        calendar.add(Calendar.YEAR, -years);
        return calendar.getTime();
    }
    /**
     * 
     * <b>Summary: </b>
     *    forwordYear  增加年
     * Date
     * @param d
     * @param years
     * @return 
     * forwordYear
     * Jan 3, 2014
     */
    public static Date forwordYear(Date d, int years)
    {
        calendar.setTime(d);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
	 * 格式化当前年日期
	 */
	public static String getCurrentYear() {
		Calendar currentYear = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(currentYear.getTime());
	}
	
	/**
	 * 获得去年日期
	 */
	public static String getLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 格式化当前年月日期
	 */
	public static String getCurYearMonth() {
		Calendar curYearMonth = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(curYearMonth.getTime());
	}
	
	/**
	 * 设置月份格式
	 */
	public static String setMonthFormat(String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = sdf.parse(month);
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 格式化当期月份
	 */
	public static String getCurrentMonth() {
		Calendar currentMonth = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(currentMonth.getTime());
	}
	
	/**
	 * 获取上月日期
	 */
	public static String getLastMonth(){
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(lastMonth.getTime());
	}
	
	/**
	 * 设置月份格式
	 */
	public static String setMonthFormat(int month){
		String reportMonth = String.valueOf(month);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = sdf.parse(reportMonth);
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime(){
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(currentTime.getTime());
	}
	
	/**
	 * 获取当前日期
	 */
	public static String getCurrentDate(){
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(currentTime.getTime());
	}
	
	
	/**
	 * 获取上月日期
	 */
	public static String getLastMonthDate(){
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(lastMonth.getTime());
	}
	
	/**
	 * 获得上上个月日期格式
	 */
	public static String getTheMonthBeforeLast () {
		Calendar monthBeforeLast = Calendar.getInstance();
		monthBeforeLast.add(Calendar.MONTH, -2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(monthBeforeLast.getTime());
	}
	
	/**
	 * 获得三个月前日期格式
	 */
	public static String getThreeMonthAgo() {
		Calendar monthBeforeLast = Calendar.getInstance();
		monthBeforeLast.add(Calendar.MONTH, -3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(monthBeforeLast.getTime());
	}
	
	/**
	 * 获取当前周在该年中的周数
	 * @return
	 */
	public static String getCurrentWeek(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
	}
	
	/**
	 * 获取上周在该年中的周数
	 * @return
	 */
	public static String getLastWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
	}
	 
	
	/**
	 * 获取上周的年信息
	 * @return
	 */
	public static String getYearByLastWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
	}
	
	/**
	 * 返回指定时间的年、月、日、周等信息
	 * @param date	指定日期，日期格式：yyyy-MM-dd
	 * @param type	返回指定日期的类型
	 * 				WEEK：WEEK_OF_YEAR	指定日期周信息（所属年的总周数）
	 * 				YEAR：YEAR			指定日期的年信息
	 * 				MONTH：				指定日期的月信息
	 * 				DAY：				指定日期的日信息（所属年的第几天）
	 * @return
	 */
	public static String getTimeByAppointDate(String date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date startDate;
		try {
			startDate = sdf.parse(date);
			calendar.setTime(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(type.equalsIgnoreCase("WEEK")){
			return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
		}else if(type.equalsIgnoreCase("MONTH")){
			return String.valueOf(calendar.get(Calendar.MONTH)+1);
		}else if(type.equalsIgnoreCase("DAY")){
			return String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
		}else if(type.equalsIgnoreCase("YEAR")){
			return String.valueOf(calendar.get(Calendar.YEAR));
		}else{
			return date;
		}
	}
	
	/**
	 * 所有描述同getTimeByAppointDate方法
	 * @param date
	 * @param type
	 * @return
	 */
	public static String getTimeLastWeekByAppointDate(String date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date startDate;
		try {
			startDate = sdf.parse(date);
			calendar.setTime(startDate);
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(type.equalsIgnoreCase("WEEK")){
			return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
		}else if(type.equalsIgnoreCase("MONTH")){
			return String.valueOf(calendar.get(Calendar.MONTH)+1);
		}else if(type.equalsIgnoreCase("DAY")){
			return String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
		}else if(type.equalsIgnoreCase("YEAR")){
			return String.valueOf(calendar.get(Calendar.YEAR));
		}else{
			return date;
		}
	}
	
	public static String getDateByFormat(Date date,String format){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	
	 
    /**
     * 获取当前系统时间，格式'yyyy-mm-dd'
     *
     * @return 当前系统时间
     */
    public static String getLocalDate() {
        return format(new Date());
    }

    public static Date getDateTime() {
		return new Date(System.currentTimeMillis());
	}
   /**
     * 格式化日期 'yyyy-mm-dd'
     *
     * @param date 待格式化的日期
     * @return 日期字符串格式 'yyyy-mm-dd',date为空时返回""
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatHH24(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 格式：dateFormat 指定的格式
     *
     * @param date       待格式化的日期
     * @param dateFormat 日期格式
     * @return 日期的字符串格式，date为空时返回""
     */
    public static String format(Date date, String dateFormat) {
        if (date == null) return "";
        Format formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
    /**
     * 
     * <b>Summary: </b>
     *    getLastDayOfMonth 获得月末
     * Date
     * @param year
     * @param month
     * @return 
     * getLastDayOfMonth
     * Nov 13, 2013
     */
    public static Date getLastDayOfMonth(int year, int month) { 
    	 Calendar cal = Calendar.getInstance(); 
    	 cal.set(Calendar.YEAR, year);// 年 
    	 cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1 
    	 cal.set(Calendar.DATE, 1);// 日，设为一号 
         cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号 
    	 cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天 
    	 return cal.getTime();// 获得月末是几号 
    }
    /**
     * 
     * <b>Summary: </b>
     *    getLastDayOfMonth
     * String
     * @param year
     * @param month
     * @return 
     * getLastDayOfMonth
     * Nov 13, 2013
     */
    public static String getLastDayOfMonth() { 
   	 Calendar cal = Calendar.getInstance();  
   	 cal.set(Calendar.DATE, 1);// 日，设为一号 
     cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号 
   	 cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天 
   	 Format formatter = new SimpleDateFormat("yyyyMMdd");
   	 return formatter.format(cal.getTime());// 获得月末是几号 
   }

    /**
     * 格式：dateFormat 指定的格式
     *
     * @param date       待格式化的日期
     * @param dateFormat 日期格式
     * @param defaultRtn date为空时的默认返回值
     * @return 日期的字符串格式
     */
    public static String format(Date date, String dateFormat, String defaultRtn) {
        if (defaultRtn == null) defaultRtn = "";
        if (date == null) return defaultRtn;
        Format formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * 将"yyyy-MM-dd"格式的字符串格式化为Date类型
     *
     * @param str 待处理的字符串
     * @return 日期，若出现异常ParseException则返回 null
     */
    public static Date parse(String str) {
        return parse(str, "yyyy-MM-dd");
    }

    /**
     * 将指定格式的字符串格式化为Date类型
     *
     * @param str        待处理的字符串
     * @param dateFormat 日期的格式
     * @return 日期，若出现异常ParseException则返回 null
     */
    public static Date parse(String str, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            if (str == null) {
                return null;
            }
            return format.parse(str);
        } catch (ParseException e) {
        	e.printStackTrace();
        	return null;
        }
    }

    /**
     * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
     *
     * @param date   日期
     * @param field  日历字段。
     * @param amount 为字段添加的日期或时间量。
     * @return 修改后的日期
     */
    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param dateStart  起始日期
     * @param dateEnd    终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitDay(String dateStart, String dateEnd, String dateFormat) {
        String[] wordLists;

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Calendar grcStart = new GregorianCalendar();
        Calendar grcEnd = new GregorianCalendar();

        if (dateStart == null || dateEnd == null) {
            wordLists = new String[1];
            wordLists[0] = dateStart;
            return wordLists;
        }
        try {
            grcStart.setTime(format.parse(dateStart));
        } catch (ParseException e) {
            grcStart = new GregorianCalendar();
        }
        try {
            grcEnd.setTime(format.parse(dateEnd));
        } catch (ParseException ex) {
            grcEnd = new GregorianCalendar();
        }
        return splitDay(grcStart, grcEnd, dateFormat);
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param grcStart   起始日期
     * @param grcEnd     终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitDay(Calendar grcStart, Calendar grcEnd, String dateFormat) {
        String[] wordLists;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        GregorianCalendar grcTmp = new GregorianCalendar();
        grcTmp.setTime(grcStart.getTime());
        int total = 0;
        while (grcTmp.get(Calendar.YEAR) < grcEnd.get(Calendar.YEAR)) {
            if (grcTmp.isLeapYear(grcTmp.get(Calendar.YEAR)))
                total += 365;
            else
                total += 366;
            grcTmp.add(Calendar.YEAR, 1);
        }
        if (grcTmp.get(Calendar.YEAR) == grcEnd.get(Calendar.YEAR))
            total += grcEnd.get(Calendar.DAY_OF_YEAR) - grcTmp.get(Calendar.DAY_OF_YEAR);
        if (total <= 0)
            return null;
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = format.format(grcStart.getTime());
            grcStart.add(Calendar.DATE, 1);
        }
        return wordLists;
    }

    /**
     * 此方法将两个日期之间的时间以monthFormat的格式转换为字符串数组
     *
     * @param startMonth  起始日期
     * @param endMonth    终止日期
     * @param monthFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitMonth(String startMonth, String endMonth, String monthFormat) {
        String[] wordLists;

        SimpleDateFormat format = new SimpleDateFormat(monthFormat);
        Calendar grcStart = new GregorianCalendar();
        Calendar grcEnd = new GregorianCalendar();

        if (startMonth == null || endMonth == null) {
            wordLists = new String[1];
            wordLists[0] = startMonth;
            return wordLists;
        }
        try {
            grcStart.setTime(format.parse(startMonth));
        } catch (ParseException e) {
            grcStart = new GregorianCalendar();
        }
        try {
            grcEnd.setTime(format.parse(endMonth));
        } catch (ParseException ex) {
            grcEnd = new GregorianCalendar();
        }
        return splitMonth(grcStart, grcEnd, monthFormat);
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param grcStart   起始日期
     * @param grcEnd     终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitMonth(Calendar grcStart, Calendar grcEnd, String dateFormat) {
        String[] wordLists;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        GregorianCalendar grcTmp = new GregorianCalendar();
        grcTmp.setTime(grcStart.getTime());
        int total = 0;
        while (grcTmp.get(Calendar.YEAR) < grcEnd.get(Calendar.YEAR)) {
            total += 12;
            grcTmp.add(Calendar.YEAR, 1);
        }
        if (grcTmp.get(Calendar.YEAR) == grcEnd.get(Calendar.YEAR))
            total += grcEnd.get(Calendar.MONTH) - grcTmp.get(Calendar.MONTH);
        if (total <= 0)
            return null;
        total++;
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = format.format(grcStart.getTime());
            grcStart.add(Calendar.MONTH, 1);
        }
        return wordLists;
    }

    /**
     * 计算两个日期之间的日期差值
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 日期1-日期2 的值
     * @since 1.1
     */
    public static int differenceOfDay(Date date1, Date date2) {
        int sign = date1.compareTo(date2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        if (sign > 0) {
            cal1.setTime(date1);
            cal2.setTime(date2);
        } else {
            cal1.setTime(date2);
            cal2.setTime(date1);
        }
        int total = 0;
        while (cal2.get(Calendar.YEAR) < cal1.get(Calendar.YEAR)) {
            total += cal2.getActualMaximum(Calendar.DAY_OF_YEAR);
            cal2.add(Calendar.YEAR, 1);
        }
        if (cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)) {
            total += cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
        }
        return sign * total;
    }

    /**
     * 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的第一日。
     *
     * @param date   日期
     * @param amount 与日期所在旬的差值量
     * @return 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的第一日
     * @since 1.1
     */
    public static Date getFirstDayPeriodOfTenDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int xunCount = (calendar.get(Calendar.DAY_OF_MONTH) - 1) / 10;
        if (xunCount == 3) xunCount = 2;
        int monthDiff;
        if ((xunCount + amount) < 0) {
            if ((xunCount + amount) / 3 == 0) monthDiff = ((xunCount + amount) / 3 - 1);
            else monthDiff = (xunCount + amount) / 3;
        } else {
            monthDiff = (xunCount + amount) / 3;
        }
        calendar.setTime(DateUtil.add(calendar.getTime(), Calendar.MONTH, monthDiff));
        if ((xunCount + amount) % 3 == 0) {
            xunCount = 0;
        } else if ((xunCount + amount) % 3 < 0) {
            xunCount = (xunCount + amount) % 3 + 3;
        } else {
            xunCount = (xunCount + amount) % 3;
        }
        switch (xunCount) {
            case 0:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 11);
                break;
            default:
                calendar.set(Calendar.DAY_OF_MONTH, 21);
        }
        return calendar.getTime();
    }

    /**
     * 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的最后一日。
     *
     * @param date   日期
     * @param amount 与日期所在旬的差值量
     * @return 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的最后一日
     * @since 1.1
     */
    public static Date getEndDayPeriodOfTenDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int xunCount = (calendar.get(Calendar.DAY_OF_MONTH) - 1) / 10;
        if (xunCount == 3) xunCount = 2;
        int monthDiff;
        if ((xunCount + amount) < 0) {
            if ((xunCount + amount) / 3 == 0) monthDiff = ((xunCount + amount) / 3 - 1);
            else monthDiff = (xunCount + amount) / 3;
        } else {
            monthDiff = (xunCount + amount) / 3;
        }
        calendar.setTime(DateUtil.add(calendar.getTime(), Calendar.MONTH, monthDiff));
        if ((xunCount + amount) % 3 == 0) {
            xunCount = 0;
        } else if ((xunCount + amount) % 3 < 0) {
            xunCount = (xunCount + amount) % 3 + 3;
        } else {
            xunCount = (xunCount + amount) % 3;
        }
        switch (xunCount) {
            case 0:
                calendar.set(Calendar.DAY_OF_MONTH, 10);
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 20);
                break;
            default:
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间的差,单位是天
     * Chivas Ju. zhuy#slof.com
     *
     * @param date1
     * @param date2
     * @return long
     * todo 待验证
     */
    private static long getDays(Date date1, Date date2) {
        long quot = 0;
        try {
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Math.abs(quot);

    }

    //第N周开始日期
    //todo 待验证
    private static Date getStartTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        int k = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_YEAR, 1 - k);
        calendar.add(Calendar.WEEK_OF_YEAR, i - 1);
        return calendar.getTime();
    }

    //第N周结束日期
    //todo 待验证
    private static Date getEndTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        int k = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_YEAR, 1 - k);
        calendar.add(Calendar.WEEK_OF_YEAR, i - 1);
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        return calendar.getTime();
    }
    /**
     *  返回年
     * @param q
     * @return
     */
    public static String getYear(Date q){
    	return format(q,"yyyy");
    }
    /**
     * 返回月
     * @param q
     * @return
     */
    public static String getMonth(Date q){
    	return format(q,"MM");
    }
    /**
     * 返回日子
     * @param q
     * @return
     */
    public static String getDay(Date q){
    	return format(q,"dd");
    }
    /**
     * 增加日期
     *
     * @param date  日期
     * @param field the calendar field
     * @param i     天数
     * @return 添加i天后的日期
     */
    public static Date addDate(Date date, int field, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, i);
        return calendar.getTime();
    }    
    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    天数
     * @return 添加i天后的日期
     */
    public static Date addDate(Date date, int i) {
        return addDate(date, Calendar.DATE, i);
    }

    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    月数
     * @return 添加i月后的日期
     */
    public static Date addMonth(Date date, int i) {
        return addDate(date, Calendar.MONTH, i);
    }

    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    年数
     * @return 添加i年后的日期
     */
    public static Date addYear(Date date, int i) {
        return addDate(date, Calendar.YEAR, i);
    }  
    
    /**
     * 传入年月，获取旬度的开始结束时间
     * @param year
     * @param month
     * @param param 1-上旬；2-中旬；3-下旬
     * @return 数组第一个为开始时间，第二个为结束时间
     */
    public static Date[] getTenDay(String year, String month, int param){
    	Date[] date = new Date[2];
    	
    	if(param==1){
    		date[0] = parse(year+"-"+month+"-01");
    		date[1] = parse(year+"-"+month+"-10");
    	}else if(param==2){
    		date[0] = parse(year+"-"+month+"-11");
    		date[1] = parse(year+"-"+month+"-20");
    	}else if(param==3){
    		date[0] = parse(year+"-"+month+"-21");
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date[0]);
    		date[1] = parse(year+"-"+month+"-" +(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
    	}
    	
    	return date;
    }
    
    public static int getLastDayOfMonth(Date date){
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static String getXd(Date date){
    	String rtn = "";
    	System.out.println(Integer.parseInt(DateUtil.getDay(date)));
    	if(Integer.parseInt(DateUtil.getDay(date))>=1 && Integer.parseInt(DateUtil.getDay(date))<11){
    		rtn = "1";
    	}else if(Integer.parseInt(DateUtil.getDay(date))>10 && Integer.parseInt(DateUtil.getDay(date))<21){
    		rtn = "2";
    	}else{
    		rtn = "3";
    	}
    	return rtn;
    }
    
    public static Timestamp getTimestamp(){
    	return  new java.sql.Timestamp(new Date().getTime());
    }
    
    /** 
	 * 根据日期获得星期 
	 * @param date 
	 * @return 
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		String[] weekDaysCode = {"0","1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}
	
	/** 
	 * 根据日期获得星期 
	 * @param date 
	 * @return 
	 */
	public static String getWeekOfDateInt(Date date) { 
		String[] weekDaysCode = {"7","1","2", "3", "4", "5", "6"};
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
		return weekDaysCode[intWeek];
	}

	/** 
	 * 获得周一的日期 
	 * 
	 * @param date 
	 * @return 
	 */
	public static String getMonday(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return dateFormat.format(calendar.getTime());

	}

	/** 
	 * 获得周三的日期 
	 * 
	 * @param date 
	 * @return 
	 */
	public static String getWednesday(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

		return dateFormat.format(calendar.getTime());

	}

	/** 
	 * 获得周五的日期 
	 * 
	 * @param date 
	 * @return 
	 */
	public static String getFriday(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

		return dateFormat.format(calendar.getTime());
	}

	/** 
	 * 当前日期前几天或者后几天的日期 
	 * @param n 
	 * @return 
	 */
	public static String afterNDay(int n) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DATE, n);

		Date date = calendar.getTime();

		String s = dateFormat.format(date);

		return s;

	}

	/** 
	 * 判断两个日期是否在同一周 
	 * 
	 * @param date1 
	 * @param date2 
	 * @return 
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周 
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	public static String getCurrentDateString(){
		Calendar currentTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(currentTime.getTime());
	}
	
	
	public static String getLastDay(String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date date = DateUtil.addDate(new Date(), -1);
		return sdf.format(date);
	}
    
}