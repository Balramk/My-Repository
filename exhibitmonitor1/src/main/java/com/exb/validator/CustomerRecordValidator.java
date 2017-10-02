package com.exb.validator;

import java.text.ParseException;
import java.util.Date;

import com.exb.dto.CustomerDto;
import com.exb.util.StringToDateConvertor;

public class CustomerRecordValidator {
	
	public static boolean isValidCustomer(CustomerDto dto) throws ParseException {
		Date dateOfBirth = StringToDateConvertor.stringToDateConvertor(dto.getDob());
		Date dateOfDeath = StringToDateConvertor.stringToDateConvertor(dto.getDod());
		
		if(dateOfBirth.after(dateOfDeath)){
			System.out.println("Customer Record Invalid : "+dto.toString());
		}
		return true;
	}
}
