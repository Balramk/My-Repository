package com.exb.service;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.exb.context.LoadApplicationContext;
import com.exb.threads.FileProcessor;

public class ExhibitMonitorApp {
	public static void main(String[] args) {
		LoadApplicationContext context = null;
		
		try {
			context = LoadApplicationContext.loadContext();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Exception occurred while loading LoadApplicationContext");
			System.out.println(e.getMessage());
		}
	
		Thread process = new Thread(new FileProcessor());
		process.run();
	}
}
