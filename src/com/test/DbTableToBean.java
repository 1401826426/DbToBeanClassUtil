package com.test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbTableToBean {
 		
   static { 
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
   
   public void startChange() throws Exception {
	   DBDefinition dbDefinition = Conf.getConf().getDbDefinition() ;  
	   Connection connection = DriverManager.getConnection(dbDefinition.getUrl(), dbDefinition.getUser(), dbDefinition.getPassword()) ;
	   DatabaseMetaData metaData = connection.getMetaData() ;
	   List<BeanDefinition> beanDefinitions = Conf.getConf().getBeanDefinitions() ;
	   Map<String,BeanDefinition> map = new HashMap<>() ; 
	   for(BeanDefinition beanDefinition:beanDefinitions) {
		   String tableName = beanDefinition.getTableName() ;
		   map.put(tableName, beanDefinition) ; 
	   } 
	   ResultSet rs = metaData.getTables(null, null,  null , new String[] {"TABLE"})  ; 
	   while(rs.next()) {
		   String tableName = rs.getString("TABLE_NAME") ; 
		   for(String confTable:map.keySet()) {
			   if(confTable.equalsIgnoreCase(tableName)) {
				    completeBeanDefinitionConfFromDb(map.get(confTable) , metaData) ; 
			   }
		   }
	   }
	   createAndSaveBeanDefinition(beanDefinitions) ; 
   }
   
	
	private void createAndSaveBeanDefinition(List<BeanDefinition> beanDefinitions) throws FileNotFoundException {
		for(BeanDefinition beanDefinition:beanDefinitions) {
			FileUtils.getInstance().writeTextToFile(beanDefinition.getPackagePath(), beanDefinition.getClassName(), beanDefinition.createBeanClass());
		}
	}


	private void completeBeanDefinitionConfFromDb(BeanDefinition beanDefinition, DatabaseMetaData metaData) throws Exception {
		ResultSet rs = metaData.getColumns(null, null, beanDefinition.getTableName(), null) ;
		List<ColumnDefinition> addColumnDefinitions = new ArrayList<>() ; 
		while(rs.next()) {
			String columnName = rs.getString("COLUMN_NAME") ;
			String columnToBeanName = BeansUtils.changeNameToHumpName(columnName) ; 
			String comment = rs.getString("REMARKS") ; 
			String dataType = rs.getString("TYPE_NAME") ; 
			List<ColumnDefinition> columnDefinitions = beanDefinition.getColumnDefinitions() ;
			ColumnDefinition target = null ; 
			for(ColumnDefinition columnDefinition:columnDefinitions) {
				if(columnDefinition.getTableColumn().equals(columnName) || columnToBeanName.equals(columnDefinition.getName())) {
					target = columnDefinition ; 
				}
			}
			if(target == null) {
				target = new ColumnDefinition() ;
				addColumnDefinitions.add(target) ; 
			}
			if(!BeansUtils.hasText(target.getName())) {
				target.setName(columnToBeanName);
			}
			if(!BeansUtils.hasText(target.getTableColumn())) {
				target.setTableColumn(columnName);
			}
			if(!BeansUtils.hasText(target.getComment())) {
				target.setComment(comment);
			}
			if(!BeansUtils.hasText(target.getType())) {
				target.setType(BeansUtils.changeDbTypeTo(dataType));
			}
		}
		beanDefinition.getColumnDefinitions().addAll(addColumnDefinitions) ;  
	}
		

	public static void main(String[] args) throws Exception {
		new DbTableToBean().startChange();  
	}
	
}
