package interview.facebook;

import java.util.ArrayList;
import java.util.HashMap;

public class IsPowerOfTwoString {
	
	public static void main(String[] args) {
		int power = power("1024");
		System.out.println(power);
		
		for(int i=0; i<31; i++) {
			String valueOf = String.valueOf((int)Math.pow(2, i));
			
			power = power(valueOf);
			System.out.println(valueOf + " ==> " + power);
		}
	}
	
	public static int power(String a) {
	    if(a == null || a.length() == 0) return 0;
	    if(a.equals("0") || a.equals("1")) return 0;
	    /*int maxLength = String.valueOf(Long.MAX_VALUE).length();
	    if(a.length() < maxLength) {
	        if(Long.highestOneBit(Long.parseLong(a)) == Long.lowestOneBit(Long.parseLong(a))) 
	            return 1;
	        else 
	            return 0;
	    }*/
	    StringBitNumber sbn = new StringBitNumber(a);
	    return sbn.isPowerOfTwo();
	}
	
	private static class StringBitNumber {
	    private static HashMap<Integer, ArrayList<Integer>> tensMap 
	                = new HashMap<Integer, ArrayList<Integer>>();
	    private ArrayList<Integer> ints = new ArrayList<Integer>();
	    public StringBitNumber(String num) {
	        convert(num);
	    }
	    
	    private void convert(String num) {
	        int tenLevel = 0;
	        for(int i = num.length()-1; i >= 0; i--) {
	            int x = num.charAt(i)-'0';
	            if(x !=0) {
	            	ArrayList<Integer> list = getTenByLevel(tenLevel);
		            for(int j=0; j<x-1; j ++) {
		                list = plus(list, getTenByLevel(tenLevel));
		            }
		            ints = plus(ints, list);
	            }
	            tenLevel++;
	        }
	    }
	    
	    private int isPowerOfTwo() {
	    	//System.out.println(ints);
	        ints.remove(ints.size()-1);
	        return ints.contains(1)?0:1;
	    }
	    
	    private static ArrayList<Integer> getTenByLevel(int level) {
	        if(tensMap.containsKey(level))
	            return tensMap.get(level);
	        
	        if(level ==0) {
	            ArrayList<Integer> list = new ArrayList<Integer>();
	            list.add(1);
	            tensMap.put(0,list);
	            return list;
	        } else {
	            ArrayList<Integer> list = getTenByLevel(level-1);
	            for(int i=0;i<9;i++) {
	                list = plus(list, getTenByLevel(level-1));
	            }
	            tensMap.put(level,list);
	            System.out.println("ten level = " +level + " "+list);
	            return list;
	        }
	    }
	    
	    public static ArrayList<Integer> plus(ArrayList<Integer> list1, ArrayList<Integer> list2) {
	        ArrayList<Integer> result = new ArrayList<Integer>();
	        ArrayList<Integer> base = null;
	        ArrayList<Integer> addup = null;
	        if(list1.size() >= list2.size()) {
	            base = list1;
	            addup = list2;
	        } else {
	            addup = list1;
	            base = list2;
	        }
	        result.addAll(base);

	        int promote = 0;
	        for(int i=0; i<addup.size();i++) {
	            int x = result.get(i) + addup.get(i) + promote;
	            if(x >= 2) {
	                promote = 1;
	                x -= 2;
	            } else {
	                promote = 0;
	            }
	            result.set(i, x);
	        }
	        
	        int position = addup.size();
	        
	        while(promote ==1) {
	        	if(position == result.size()) {
	                result.add(1);
	                return result;
	            }
	            int x = result.get(position) + promote;
	            if(x >= 2) {
	                promote = 1;
	                x -= 2;
	            } else {
	                promote = 0;
	            }
	            result.set(position, x);
	            position++;
	        }
	        return result;
	    }
	}
}