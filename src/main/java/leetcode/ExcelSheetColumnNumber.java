package leetcode;

import java.util.HashMap;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * Input: "A"
 * Output: 1
 * 
 * Input: "AB"
 * Output: 28
 * 
 * */

public class ExcelSheetColumnNumber {

	public static void main(String[] args) {
		
		String title = "AB";
		System.out.println(titleToNumber(title));
	}
	
	public static int titleToNumber(String s) {
		HashMap<Character, Integer> titleNumberMap = new HashMap<>();
		int i = 1;
		for(char a = 'A'; a <='Z'; a ++) {
			titleNumberMap.put(a, i++);
		}
		
		int result = 0;
		for(i = s.length()-1; i >=0; i --) {
			result = result + (int)(Math.pow(26, s.length()-1-i)) *titleNumberMap.get(s.charAt(i));
		}
		return result;
	}
	
	//don't use map
	public int titleToNumber1(String a) {
	    int result = 0;
	    
	    for(int i=a.length()-1; i >=0; i--) {
	        char c = a.charAt(i);
	        int order = getOrder(c);
	        result += (int)(Math.pow(26, (a.length()-1-i)))*order;
	    }
	    return result;
	}
	public int getOrder(char c) {
	    return (int)(c-'A')+1;
	}

}
