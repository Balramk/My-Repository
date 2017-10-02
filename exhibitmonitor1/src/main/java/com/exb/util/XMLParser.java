package com.exb.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	private static XMLParser parser = null;
	private Map<String, String> xmlFileMap = null;
	
	private XMLParser() throws SAXException, IOException, ParserConfigurationException {
	
		File xmlFile = new File("exhibit-monitor.xml");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		
		NodeList nodeList = document.getElementsByTagName("input-file");
		
		for(int i=0; i<nodeList.getLength();i++){
			String fileName = null;
			String time = null;
			
			Node node = nodeList.item(i);

			if(node != null){
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element nodeElement = (Element) node;
					fileName = nodeElement.getAttribute("name");
					time = nodeElement.getAttribute("time");
				}
			}
			//If map is no created
			if(xmlFileMap == null){
				xmlFileMap = new HashMap<String, String>();
			}
			
			if(!xmlFileMap.containsKey(fileName) && fileName != null && time != null){
				xmlFileMap.put(fileName, time); //store file name & time into map
			}
		}
	}
	
	public Map<String, String> getXMLFileMap() {
		return xmlFileMap;
	}
	
	public static XMLParser getXMLParser() throws SAXException, IOException, ParserConfigurationException{
		if(parser == null){
			synchronized(XMLParser.class) {
				if(parser==null) {
					parser = new XMLParser();
				}
			}
		}
		return parser;
	}
}
