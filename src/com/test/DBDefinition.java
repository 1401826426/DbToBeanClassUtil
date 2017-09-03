package com.test;

import java.util.Iterator;

import org.dom4j.Element;

public class DBDefinition {
	
	private String url ; 
	
	private String user ; 
	
	private String password ;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	public static DBDefinition resolveDbDefinition(Element e) {
		if(!Constants.ELEMENT_DB_NAME.equals(e.getName())) {
			return null ; 
		}
		DBDefinition dbDefinition = new DBDefinition() ; 
		Iterator<Element> iterator = e.elementIterator() ; 
		while(iterator.hasNext()) {
			Element element = iterator.next() ; 
			if(Constants.ELEMENT_URL.equals(element.getName())) {
				dbDefinition.setUrl(element.getStringValue());
			}
			if(Constants.ELEMENT_PASSWORD.equals(element.getName())) {
				dbDefinition.setPassword(element.getStringValue());
			}
			if(Constants.ELEMENT_USER.equals(element.getName())) {
				dbDefinition.setUser(element.getStringValue());
			}
		}	
		return dbDefinition  ; 
	}

	@Override
	public String toString() {
		return "DBDefinition [url=" + url + ", user=" + user + ", password=" + password + "]";
	}

	
}
