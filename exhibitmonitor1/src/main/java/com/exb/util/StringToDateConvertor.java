package com.exb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConvertor {
	private static final String dateFormat = "dd-MMM-yyyy";
	
	public static Date stringToDateConvertor(String sDate) throws ParseException{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		if(sDate != null && sDate.length() > 0){
			date = sdf.parse(sDate);
		}
		return date;
	}
}
