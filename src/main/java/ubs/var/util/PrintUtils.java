package ubs.var.util;

public class PrintUtils {
	private static String prefix() {
		String className = Thread.currentThread().getStackTrace()[3].getClassName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		String prefix = className + ":" + methodName + "\t";
		return prefix;
	}
	
	public static void log(String message) {
		System.out.println(prefix()+message); 
	}
	
	public static void error(String message) {
		System.err.println(prefix()+message); 
	}
	
	public static void warn(String message) {
		System.err.println(prefix()+message); 
	}
	
	public static void exception(Exception e) {
		System.err.println(prefix()+e.getMessage()); 
	}
	
	public static void print(Object message) {
		System.out.println(message); 
	}
	
	public static void print(Object... message) {
		for(Object o : message) {
			System.out.print(o + "\t"); 
		}
		System.out.println();
	}
}
