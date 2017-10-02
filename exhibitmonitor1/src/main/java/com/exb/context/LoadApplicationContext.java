package com.exb.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.exb.util.XMLParser;

public class LoadApplicationContext {
	private static XMLParser parser = null;
	private static Map<String, String> xmlFileMap = null;
	private static LoadApplicationContext context = null;
	public static Map<String, String> proFileMap = null;
	
	static {
		try{
			parser = XMLParser.getXMLParser();
			xmlFileMap = parser.getXMLFileMap();
			proFileMap = new HashMap<String, String>();
			proFileMap.put("b.csv", "29-09-2017");
			
		}catch (SAXException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	private LoadApplicationContext() {
		//No code
	}
	public static LoadApplicationContext loadContext()throws SAXException, IOException, ParserConfigurationException {
		if(context == null) {
			synchronized(LoadApplicationContext.class) {
				if(context == null) {
					context = new LoadApplicationContext();
				}
			}
		}
		return context;
	}
}
