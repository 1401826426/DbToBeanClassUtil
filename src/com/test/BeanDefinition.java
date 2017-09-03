package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class BeanDefinition {
	
	private String tableName ; 
	
	private String packagePath = Constants.DEFAULT_PACKAGE ; 
	
	private String superClass ; 
	
	private String className ; 
	
	private List<String> superInterfaces ; 
	
	private List<ColumnDefinition> columnDefinitions ;
	
	public boolean hasDate() {
		for(ColumnDefinition columnDefinition:columnDefinitions) {
			if(Constants.TYPE_DATE.equals(columnDefinition.getType())) {
				return true ; 
			}
		}
		return false ; 
	}
	
	
	public String getClassName() {
		if(className == null && tableName != null) {
			try {
				className = BeansUtils.changeNameToHumpName(tableName) ;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		if(BeansUtils.isLowerCase(className.charAt(0))){
			className = BeansUtils.initialCharUp(className) ; 
		}
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getSuperInterfaces() {
		return superInterfaces;
	}

	public void setSuperInterfaces(List<String> superInterfaces) {
		this.superInterfaces = superInterfaces;
	}

	public List<ColumnDefinition> getColumnDefinitions() {
		if(columnDefinitions == null) {
			columnDefinitions = new ArrayList<>() ;
		}
		return columnDefinitions;
	}

	public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	} 
	
	public static BeanDefinition resolvBeanDefinition(Element element) {
		if(!Constants.ELEMENT_BEAN.equals(element.getName())) {
			return null ; 
		}
		BeanDefinition beanDefinition = new BeanDefinition() ;
		Iterator<Attribute> attributes = element.attributeIterator() ;
		while(attributes.hasNext()) {
			Attribute attribute = attributes.next() ; 
			if(Constants.ATTRIBUTE_TABLE_NAME.equals(attribute.getName())) {
				beanDefinition.setTableName(attribute.getValue());
			}
			if(Constants.ATRRIBUT_PACKAGE.equals(attribute.getName())) {
				beanDefinition.setPackagePath(attribute.getValue());
			}
			if(Constants.ATTRIBUTE_CLASS_NAME.equals(attribute.getName())) {
				beanDefinition.setClassName(attribute.getValue());
			}
		}
		Iterator<Element> it = element.elementIterator() ; 
		while(it.hasNext()) {
			Element e = it.next() ; 
			if(Constants.ELEMENT_SUPPER_CLASS.equals(e.getName())) {
				beanDefinition.setSuperClass(e.getStringValue());
			}
			if(Constants.ELEMENT_PACKAGE.equals(e.getName())) {
				beanDefinition.setPackagePath(e.getStringValue());
			}
			if(Constants.ELEMENT_SUPPER_INTERFACES.equals(e.getName())) {
				Iterator<Element> interfaceNodes = e.elementIterator() ; 
				List<String> interfaces = new ArrayList<>() ; 
				while(interfaceNodes.hasNext()) {
					Element interfaceNode = interfaceNodes.next() ; 
					if(Constants.ELEMENT_SUPPER_INTERFACE.equals(interfaceNode.getName())) {
						interfaces.add(interfaceNode.getStringValue()) ; 
					}
				}
				beanDefinition.setSuperInterfaces(interfaces);
			}
			if(Constants.ELEMENT_SUPPER_CLASS.equals(e.getName())) {
				beanDefinition.setSuperClass(e.getStringValue());
			}
			if(Constants.ELEMENT_CLASS_NAME.equals(e.getName())) {
				beanDefinition.setClassName(e.getStringValue());
			}
			if(Constants.ELEMENT_COLUMNS.equals(e.getName())) {
				Iterator<Element> columnNodes = e.elementIterator() ;
				List<ColumnDefinition> columnDefinitions = new ArrayList<>() ; 
				while(columnNodes.hasNext()) {
					Element columnNode = columnNodes.next() ; 
					ColumnDefinition columnDefinition = ColumnDefinition.resolveColumnDefinition(columnNode) ;
					if(columnDefinition != null) {
						columnDefinitions.add(columnDefinition) ;
					}
				}
				beanDefinition.setColumnDefinitions(columnDefinitions);
			}
		}
 		return beanDefinition ; 
	}

	@Override
	public String toString() {
		return "BeanDefinition [tableName=" + tableName + ", packagePath=" + packagePath + ", superClass=" + superClass
				+ ", superInterfaces=" + superInterfaces + ", columnDefinitions=" + columnDefinitions + "]";
	}
	
	public String createBeanClass() {
		String bean = "package " + getPackagePath() + ";\n" ;
		if(getSuperClass() != null) {
			bean += "import "+ getSuperClass() + ";\n" ;
		}
		if(getSuperInterfaces()!=null) {
			for(String superInterface:getSuperInterfaces()) {
				bean += "import " + superInterface  + ";\n";
			}
		}
		if(hasDate()) {
			bean += "import java.util.Date;\n" ; 
		}
		bean += "\n" ; 
		bean += "public class " + getClassName() +" "; 
		if(getSuperClass() != null) {
			bean += "extends" + " " + BeansUtils.getClassNameFromCompleteName(getSuperClass()) +" "; 
		}
		boolean flag = false ; 
		if(getSuperInterfaces() != null && getSuperInterfaces().size() > 0) {
			for(String superInterface:getSuperInterfaces()) {
				if(flag) {
					bean += "," ;  
				}else {
					bean += "implements "  ;
				}
				bean += BeansUtils.getClassNameFromCompleteName(superInterface) ; 
			}
		}
		bean += "{\n\n" ; 
		for(ColumnDefinition columnDefinition:getColumnDefinitions()) {
			bean += "\t//" + columnDefinition.getComment() + "\n"; 
			bean += "\tprivate "+ columnDefinition.getType() + " " + columnDefinition.getName() ; 
			if(columnDefinition.getDefaultVale() != null) {
				bean += " = " + columnDefinition.getDefaultVale() ; 
			}
			bean += ";\n\n" ; 
		}
		for(ColumnDefinition columnDefinition:getColumnDefinitions()) {
			String name = columnDefinition.getName() ; 
			name = BeansUtils.changeLowerToUp(name.charAt(0)) + name.substring(1) ;
			bean += "\tpublic void set" + name + "("+ columnDefinition.getType() + " " + columnDefinition.getName() + "){\n" ;
			bean += "\t    this." + columnDefinition.getName() + " = " + columnDefinition.getName() +";\n" ; 
			bean += "\t}\n\n" ; 
			bean += "\tpublic " + columnDefinition.getType()  + " get" + name +"(){\n" ; 
			bean += "\t    return " + columnDefinition.getName()  + ";\n"; 
			bean += "\t}\n\n" ; 
		}
		bean += "}" ; 
		return bean ; 
	}
	
}










