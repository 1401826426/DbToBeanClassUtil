package com.test;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class ClassPathDocumentReader {
	
	private final static SAXReader reader = new SAXReader() ; 
	
	
	public static Document getDocument(String name) {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(name) ; 
		try {
			return reader.read(inputStream) ;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ; 
	}
	
	
//	private final static Doce

}
