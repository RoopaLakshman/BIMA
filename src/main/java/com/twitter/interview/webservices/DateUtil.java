package com.twitter.interview.webservices;

import java.util.Date;

public class DateUtil {

	static final long DAY = 24 * 60 * 60 * 1000;
	
	public static boolean inLastDay(Date aDate) {
	    return aDate.getTime() > System.currentTimeMillis() - DAY;
	}
	
}
