package com.cerenlacinel.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {

	public static String getCurrentDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
}
