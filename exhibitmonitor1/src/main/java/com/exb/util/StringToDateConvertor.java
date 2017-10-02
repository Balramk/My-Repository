package com.exb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConvertor {
	
	public static Date stringToDateConvertor(String sDate) throws ParseException{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		if(sDate != null){
			if(sDate.length() >= 0){
				date = sdf.parse(sDate);
			}
		}
		return date;
	}
}
