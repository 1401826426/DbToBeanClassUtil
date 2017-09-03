package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;


public class Conf{
	
	private final static String CONF_NAME = "conf.xml" ; 
	
	private final static Map<String, Object> map = new HashMap<>() ;
	
	public static final Conf conf = new Conf();  
	
	private Conf() {
		refresh(); 
	}
	
	public static Conf getConf() {
		return conf ; 
	}
	
	public void refresh() {
		Document doc = ClassPathDocumentReader.getDocument(CONF_NAME) ; 
		Element root = doc.getRootElement();
		if(Constants.ELEMENT_ROOT_NAME != root.getName()) {
			System.err.println("根目录解析出错");
		}
		Iterator<Element> it= root.elementIterator() ;
		while(it.hasNext()) {
			Element element = it.next() ; 
			if(Constants.ELEMENT_DB_NAME.equals(element.getName())) {
				DBDefinition dbDefinition = resolveDB(element) ;
				if(dbDefinition != null) {
					map.put(Constants.ELEMENT_DB_NAME,dbDefinition) ; 
				}
			}else if(Constants.ElEMENT_BEANS.equals(element.getName())) {
				List<BeanDefinition> beanDefinitions = resloveBeans(element) ; 
				map.put(Constants.ElEMENT_BEANS , beanDefinitions) ; 
			}
		}
	}

	private List<BeanDefinition> resloveBeans(Element element) {
		Iterator<Element> it = element.elementIterator() ; 
		List<BeanDefinition> beanDefinitions = new ArrayList<>() ; 
		while(it.hasNext()) {
			Element e = it.next() ; 
			if(Constants.ELEMENT_BEAN.equals(e.getName())) {
				BeanDefinition beanDefinition = BeanDefinition.resolvBeanDefinition(e) ; 
				beanDefinitions.add(beanDefinition) ; 
			}
		}
		return beanDefinitions ; 
	}

	private DBDefinition resolveDB(Element element) {
		return DBDefinition.resolveDbDefinition(element) ; 
	}
	
	
	public Object get(String name) {
		return map.get(name) ; 
	}
	
	public DBDefinition getDbDefinition() {
		return (DBDefinition)map.get(Constants.ELEMENT_DB_NAME) ; 
	}
	
	@SuppressWarnings("unchecked")
	public List<BeanDefinition> getBeanDefinitions(){
		return (List<BeanDefinition>)map.get(Constants.ElEMENT_BEANS) ; 
	}
	
	public static void main(String[] args) {
		List<BeanDefinition> beanDefinitions = Conf.getConf().getBeanDefinitions() ; 
		for(BeanDefinition beanDefinition: beanDefinitions) {
			System.out.println(beanDefinition);
		}
		DBDefinition dbDefinition = Conf.getConf().getDbDefinition() ; 
		System.out.println(dbDefinition);
	}
	
}






