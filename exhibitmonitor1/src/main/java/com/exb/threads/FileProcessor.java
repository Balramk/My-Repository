package com.exb.threads;

import java.io.File;
import java.text.ParseException;

import com.exb.util.PollerCheck;

public class FileProcessor extends PollerCheck implements Runnable{
	@Override
	public void run() {
			File files[] = getFiles("H:\\MyDir\\InputFiles");
			
			if(files.length == 0){
				System.out.println("Drive is empty!");
				return;
			}
			
			for(File file : files){
				try {
					//Delete file which is invalid, duplicate & not on time
					if(!isValid(file) || isDuplicate(file) || !isOnTime(file)){
						deleteFile(file);
						System.out.println(file.getName() + " is deleted successfully!");
					}
					else{	
						//Valid file move to process folder
						moveProcessedFile(file);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
	}
}
