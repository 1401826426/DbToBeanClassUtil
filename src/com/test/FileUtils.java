package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileUtils {
	
	private static FileUtils instance = new FileUtils() ; 
	
	private FileUtils() {
		
	}
	
	public static FileUtils getInstance() {
		return instance ; 
	}
	
	public  File checkAndCreatePackage(String path) { 
		path = BeansUtils.changePackageToPath(path) ;
		String parentPath = System.getProperty("user.dir") + File.separator + "src";
		String realPath = parentPath + File.separator + path ;
		File file = new File(realPath) ; 
		if(!file.exists()) {
			file.mkdirs() ; 
		}
		return file  ; 
	}
	
	
	public OutputStream getClassPathOutputStream(String path , String className) throws FileNotFoundException {
		File pathFile  = checkAndCreatePackage(path);
		if(!className.endsWith(Constants.JAVA_SUFFIX)) {
			className += Constants.JAVA_SUFFIX ; 
		}
		File file = new File(pathFile , className) ; 
		return new FileOutputStream(file) ; 
	}
	
	public void writeTextToFile(String path , String className , String text) throws FileNotFoundException {
		OutputStream os = getClassPathOutputStream(path, className) ; 
		PrintWriter pw = new PrintWriter(os) ; 
		pw.write(text);
		pw.close();
 	}
	
	
	public static void main(String[] args) {
		FileUtils.getInstance().checkAndCreatePackage("com.test.beans");
	}
	
}
