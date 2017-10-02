package com.exb.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import com.exb.context.LoadApplicationContext;

public class PollerCheck {
	private Map<String, String> tempMap = null;
	
	public void moveProcessedFile(File f)
	{   
		File pFile = new File("H:\\MyDir\\ProcessedFile\\"+f.getName());
		System.out.println("Processed file moved to : "+pFile.getAbsolutePath());
	}
	public static File[] getFiles(String filePath){
		File folder = new File(filePath);
		File fileList[] = folder.listFiles();
		
		return fileList;
	}
	public boolean deleteFile(File file){
		return file.delete();
	}
	
	public boolean isValid(File file) {
		tempMap = LoadApplicationContext.proFileMap;
		if(tempMap != null && tempMap.containsKey(file.getName())){
		    return true;	
		}else {
			System.out.println(file.getName()+" is invalid file");
			return false;
		}
	}
	
	public boolean isOnTime(File file) throws ParseException {
		tempMap = LoadApplicationContext.proFileMap;
		
		if(isValid(file) == true) {
			String expectedTime = tempMap.get(file.getName());
			long actualTime = file.lastModified();
			
			SimpleDateFormat dateFormate = new SimpleDateFormat("dd MMM yyy HH:mm:ss");
			Date eTime = dateFormate.parse(expectedTime);
			
			Calendar expTime = new GregorianCalendar();
			Calendar actTime = new GregorianCalendar();
			expTime.setTime(eTime);
			actTime.setTimeInMillis(actualTime);
			long td = expTime.getTimeInMillis() - actTime.getTimeInMillis();
			if(td>0) {
				System.out.println(file.getName()+" is recieved on time");
				return true;
			}else{
				System.out.println(file.getName()+" is not recieved on time");
			}
		}
		return false;
	}
	
	public boolean isDuplicate(File file) throws ParseException{
		tempMap = LoadApplicationContext.proFileMap;
		
		if(tempMap != null && tempMap.containsKey(file.getName())){
			String pDate = tempMap.get(file.getName());
			//String currentDate = new Date().toString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			if((sdf.parse(pDate)).equals(sdf.format(new Date()))){
				System.out.println(file.getName() + " has been already processed.");
				return true;
			}
		}
		return false;
	}
}
