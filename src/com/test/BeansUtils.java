package com.test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeansUtils {
	
	private final static char UNDER_LINE = '_' ; 
	
	private static Set<String> illegalNameSet = new HashSet<>() ; 
	
	private static String[] illegalNames = new String[] {
			"abstract","assert","boolean","break","byte","case","catch","char","class","const","continue",
			"default","do","double","else","enum","extends","final","finally","float","for","goto","if",
			"implements","import","instanceof","int","interface","long","native","new","package","private",
			"protected","public","return","short","static","strictfp","super","switch","synchronized","this",
			"throw","throws","transient","try","void","volatile","while"
	} ; 
	
	static {
		illegalNameSet.addAll(Arrays.asList(illegalNames)) ;
	}
	
	public static String changeNameToHumpName(String name) throws Exception {
		if(illegalNameSet.contains(name.toLowerCase())) {
			throw new Exception(name + "不能作为变量") ;
		}
		StringBuilder sb = new StringBuilder() ;
		char[] chs = name.toCharArray() ;
		boolean lastCharUnderLine = false ; 
		int i = 0  ; 
		while(Constants.ASCII_UNDER_LINE == chs[i]) {
			i++ ; 
		}
		for(;i < chs.length;i++) {
			char c = chs[i] ; 
			if(UNDER_LINE == c) {
				lastCharUnderLine = true ; 
				continue ; 
			}else if(isLowerCase(c)) {
				if(lastCharUnderLine) {
					c = changeLowerToUp(c) ; 
				}
			}else if(isUpCase(c)) {
				if(!lastCharUnderLine) {
					c = changeUpToLower(c) ;
				}
			}else if(isNumber(c)) {
				if(i == 0) {
					throw new Exception(name + "不能作为变量") ; 
				}
			}else if(!isDoller(c)){
				throw new Exception(name + "不能作为变量") ;
			}
			sb.append(c) ; 
			lastCharUnderLine = false ; 
		}
		return sb.toString() ; 
	}
	
	public static String initialCharUp(String name) {
		return String.valueOf(changeLowerToUp(name.charAt(0))) + name.substring(1) ; 
	}
	
	private static boolean isDoller(char c) {
		return c == Constants.ASCII_DOLLER;
	}

	public static boolean  isLowerCase(char c) {
		return c >= 'a' && c <= 'z' ; 
	}
	
	public static boolean isUpCase(char c) {
		return c >= 'A' && c <= 'Z' ; 
	}
	
	public static boolean isNumber(char c) {
		return c >= '0' && c <= '9' ; 
	}
	
	public static char changeLowerToUp(char c) {
		if(isLowerCase(c)) {
			return (char) (c - 'a' + 'A') ; 
		}
		return c ; 
	}
	
	public static char changeUpToLower(char c) {
		if(isUpCase(c)) {
			return (char) (c - 'A' + 'a') ; 
 		}
		return c;  
	}
	
	public static boolean hasText(String name) {
		if(name == null)return false ; 
		return name.trim().length() > 0 ; 
	}
	
	public static String changeDbTypeTo(String typeName) {
		String type = "" ; 
		typeName = typeName.toUpperCase() ; 
		switch(typeName) {
		   case "CHAR":
		   case "VARCHAR":
			   type = Constants.TYPE_STRING; 
			   break ; 
		   case "FLOAT":
			   type = Constants.TYPE_FLOAT ;
			   break ; 
		   case "DOUBLE":
			   type = Constants.TYPE_DOUBLE ; 
			   break ; 
		   case "TINYINT":
			   type = Constants.TYPE_BYTE ; 
			   break ; 
		   case "INT":
			   type = Constants.TYPE_INT ; 
			   break ; 
		   case "DATE":
		   case "TIME":
		   case "TIMESTAMP":
		   case "DATETIME":
			   type = Constants.TYPE_DATE ; 
			   break ; 
		   default: 
			   type = Constants.TYPE_STRING ; 
			   break ; 
		}
		return type ; 
	}
	
	
	public static String changePackageToPath(String path) {
		return path.replace('.', File.separatorChar) ; 
	}
	
	public static String getClassNameFromCompleteName(String name) {
		return name.substring(name.lastIndexOf(".")+1) ; 
	}
	
	public static void main(String[] args)  {
		
		String[] testNames = new String[] {
				"__a" , "1_a" , "a_b" , "a_bB" , "BBB" , "$$$$" , 
				"$$_$$" , "8_9" , "8+9" , "a+b" , "a_" , "a___a" , 
				"a___" , "666" , "aBbB" , "int" , "CHAR" ,"TEST" , "aB_Ba"
		} ; 
		for(int i = 0;i < testNames.length ;  i++) {
			try {
				String name = BeansUtils.changeNameToHumpName(testNames[i]) ;
				System.out.println(testNames[i] + ">>>>>>>>>>>>>>>>>" + name);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			System.out.println("===================================================");
		}
	}
	
}
