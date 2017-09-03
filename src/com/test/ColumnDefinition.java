package com.test;

import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class ColumnDefinition {

	private String name ; 
	
	private String type ; 
	
	private String defaultVale  ; 
	
	private String tableColumn ; 
	
	private String comment = ""; 
	
	
	
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getTableColumn() {
		return tableColumn;
	}


	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}

	public String getName() {
		if(name == null && tableColumn != null) {
			try {
				name = BeansUtils.changeNameToHumpName(tableColumn) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDefaultVale() {
		return defaultVale;
	}


	public void setDefaultVale(String defaultVale) {
		this.defaultVale = defaultVale;
	}


	
	
	public static ColumnDefinition resolveColumnDefinition(Element column) {
		if(!Constants.ELEMENT_COLUMN.equals(column.getName())) {
			return null ; 
		}
		ColumnDefinition columnDefinition = new ColumnDefinition() ;
		Iterator<Attribute> attributes = column.attributeIterator() ; 
		while(attributes.hasNext()) {
			Attribute attribute = attributes.next() ; 
			if(Constants.ATTRIBUTE_TABLE_COLUMN.equals(attribute.getName())) {
			columnDefinition.setTableColumn(attribute.getStringValue());
			}
		}
		Iterator<Element> it = column.elementIterator() ; 
		while(it.hasNext()) {
			Element element = it.next() ; 
			if(Constants.ELEMENT_NAME.equals(element.getName())) {
				columnDefinition.setName(element.getStringValue());
			}
			if(Constants.ELEMENT_TYPE.equals(element.getName())) {
				columnDefinition.setType(element.getStringValue());
			}
			if(Constants.ELEMENT_DEFAULT_VALUE.equals(element.getName())) {
				columnDefinition.setDefaultVale(element.getStringValue());
			}
			if(Constants.ELEMENT_COMMENT.equals(element.getName())) {
				columnDefinition.setDefaultVale(element.getStringValue());
			}
		}
		return columnDefinition ; 
		
	}


	@Override
	public String toString() {
		return "ColumnDefinition [name=" + name + ", type=" + type + ", defaultVale=" + defaultVale + "]";
	}

	
}
