package com.happyjob.wagesteward.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return formatter.format(new Date());


	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @param date1
	 *            2012-5-11
	 * @param date2
	 *            2012-5-11
	 * @return date1大于date2 为 true,date1小于等于date2 为 false
	 */
	public static boolean compareDate2(String date1, String date2,String format) {
		DateFormat df = new SimpleDateFormat(format);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);

		if (result <= 0) {
			return false;
		} else if (result > 0) {
			return true;
		}
		return true;
	}

	/**
	 * 比较传入的日期是否大于当前日期。如果大于等于为true。小于为false
	 * 
	 * @param date1
	 *            2012-5-11
	 * @param date2
	 *            2012-5-11
	 * @return date1大于等于date2 为 true,date1小于date2 为 false
	 */
	public static boolean compareCurrDate(String date1,String format) {
		DateFormat df = new SimpleDateFormat(format);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Date dt=new Date();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(dt.toString()));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);

		if (result < 0) {
			return false;
		} else if (result >= 0) {
			return true;
		}
		return true;
	}

	
	/**
	 * 比较两个日期的是否相等
	 * 
	 * @param date1
	 *            2012-5-11
	 * @param date2
	 *            2012-5-11
	 *            
	 *            "yyyy-MM-dd"
	 * @return date1等于date2 为 true,date1不于等于date2 为 false
	 */
	public static boolean compareEqualeDate(String date1, String date2,String format) {
		DateFormat df = new SimpleDateFormat(format);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);

		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
