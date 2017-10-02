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
	
	public boolean isValid(File file) throws ParseException {
		tempMap = LoadApplicationContext.proFileMap;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		
		if(tempMap != null){
			if(tempMap.containsKey(file.getName())){
				String pDate = tempMap.get(file.getName());
				String cDate = new Date().toString();
			
				if((sdf.parse(pDate)).equals(cDate)){
					System.out.println(file.getName() + " already processed.");
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isOnTime(File file) throws ParseException {
		tempMap = LoadApplicationContext.proFileMap;
		
		if(isValid(file)) {
			String expectedTime = tempMap.get(file.getName()); //a.txt 10:32
			long actualTime = file.lastModified();
			
			SimpleDateFormat dateFormate = new SimpleDateFormat("ss MMM yyy HH:mm:ss");
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
	
	public boolean isDuplicate(File f) throws ParseException{
		tempMap = LoadApplicationContext.proFileMap;
		
		if(tempMap != null && tempMap.containsKey(f.getName())){
			String processeddate = tempMap.get(f.getName());
			String currentDate = new Date().toString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			if((sdf.parse(processeddate)).equals(currentDate)){
				System.out.println(f.getName() + " has been already processed.");
				return true;
			}
		}
		return false;
	}
}
