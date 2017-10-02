package com.exb.threads;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.omg.CORBA.CurrentHolder;

import com.exb.dto.CustomerDto;

public class CSVFileParser implements Runnable{
	private static final String COMMA_DELIMITER = ",";
	private File file = null;
	
	public CSVFileParser(File file) {
		this.file = file;
	}
	@Override
	public void run() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			buffer.readLine();
			String record = "";
			while((record = buffer.readLine()) != null){
				String[] custDetails = record.split(COMMA_DELIMITER);
				// 400 | Trisha,Yadav | 30/03/1989 | 27/04/2017 | 44000
				CustomerDto customerDto = new CustomerDto(Integer.parseInt(custDetails[0]),
						custDetails[1],custDetails[2],custDetails[3],custDetails[4],
						Float.parseFloat(custDetails[5]));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
