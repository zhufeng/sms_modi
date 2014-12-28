package com.zhufeng.sms_mod;

import java.util.*;
import java.text.*;

/*
 * 时间转换小程序：
 *
 * 先显示当前时间，以标准Date类型时间、
 *			Date类型的getTime()方法【即1970年1月1日 00:00:00 GMT以来的毫秒数】
 *			和格式化后的显示方式  这三种方式显示出来。
 * 格式化时间使用SimpleDateFormat类提供的方法来进行，此处设定的显示方式是：
 *				yyyy-MM-DD HH:mm:ss，类似于2012-01-23 20:27:30的格式
 * 
 * 第二个函数实现把1970至目标时间所得的毫秒数反向转换到标准日期时间显示和模式化显示。
 * 使用javax.swing的JOptionPane.showInputDialog方法输入，
 * 然后把输入的毫秒数用Long.parseLong()转换成long型数据，
 * 然后再使用Date类型的setTime()方法来构造并转换一个Date对象，即实现了显示方式的转换，
 * 最后再使用SimpleDateFormat来转换成预定义的格式化的时间日期显示。
 * */

public class DateFormatConv {
	Date dt = new Date();
	static Date date_user_input = new Date();
	static SimpleDateFormat date_formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public void getCurrentTime() {
		System.out.println("当前时间是(按CST时间显示)：" + "\n" + dt + "\n");
		System.out.println("当前时间是(按从1970至现在的毫秒显示)：" + "\n" + dt.getTime()
				+ "\n");
		System.out.println("当前时间格式化后的时间为：" + "\n" + date_formatter.format(dt)
				+ "\n");
	}

	public static String convertDateFormat(String date) {
		String date_converted = null;
		long dt_user_in = Long.parseLong(date);

		// System.out.println("传入的初始时间值为：" + dt_user_in);
		date_user_input.setTime(dt_user_in);
		date_converted = date_formatter.format(date_user_input);
		System.out.println("传入时间值格式化后的时间为：" + date_converted);

		return date_converted;
	}

//	public static void main(String[] args) {
//		DateFormatConv conv1 = new DateFormatConv();
//		conv1.getCurrentTime();
//		System.out.println("****************************");
//		// conv1.convertDateFormat();
//		System.exit(0);
//	}
}
