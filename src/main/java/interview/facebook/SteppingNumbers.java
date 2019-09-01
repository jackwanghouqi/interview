package interview.facebook;

import java.util.ArrayList;
import java.util.TreeSet;

public class SteppingNumbers {

	public static void main(String[] args) {
		
		System.out.println(getSteppingNumbers(10, 19));
		System.out.println();
		System.out.println(getSteppingNumbers(1, 111768769));
		System.out.println();
		System.out.println(stepnum(1, 111768769));
	}
	
	public static ArrayList<Integer> getSteppingNumbers(int from, int to) {
		int toAbs = Math.abs(to);//TODO if from is negative ?
		int tolength = String.valueOf(toAbs).length();
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<ArrayList<Integer>> wholeSteppingNumbers = new ArrayList<>();
		wholeSteppingNumbers.add(new ArrayList<Integer>());
		for(int i=0; i <10; i ++) {
			wholeSteppingNumbers.get(0).add(i);
		}
		
		int level = 1;
		while(level < tolength) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			wholeSteppingNumbers.add(list);
			ArrayList<Integer> listLowerLevel = wholeSteppingNumbers.get(level-1);
			for (Integer integer : listLowerLevel) {
				int topDigital2 = getTopDigital(integer, level);
				if(topDigital2 + 1 <10) {
					list.add((int)Math.pow(10, level)*(topDigital2 + 1)+integer);
				}
				if(topDigital2 - 1 > -1) {
					list.add((int)Math.pow(10, level)*(topDigital2 - 1)+integer);
				}
			}
			level ++;
		}
		
		TreeSet<Integer> set = new TreeSet<>();
		if(from >= 0) {
			int fromlength = String.valueOf(from).length();
			for(int i=fromlength-1; i<tolength;i++) {
				ArrayList<Integer> list = wholeSteppingNumbers.get(i);
				for(int steppingNumber : list) {
					if(steppingNumber >= from && steppingNumber <= to ) {
						set.add(steppingNumber);
					}
				}
			}
		} else {
			//do we consider negative numbers?
		}
		result.addAll(set);
		return result;

	}
	
	private static int getTopDigital(int i, int level) {
		if(String.valueOf(i).length() < level)
			return 0;
		return Integer.parseInt(String.valueOf(i).charAt(0)+"");
	}
	
	
	//Solution 2
	
	public static ArrayList<Integer> stepnum(int a, int b) {
	    
	    ArrayList<Integer> list = new ArrayList<>();
	    for(int i=a; i <=b;i++) {
	        if(isStepNum(i)) {
	            list.add(i);
	        }
	    }
	    return list;
	}
	
	private static boolean isStepNum(int i) {
	    int prevDigital = -1;
	    while(i> 0) {
	        int currDigital = i%10;
	        if(prevDigital != -1 && (int)Math.abs(currDigital-prevDigital) !=1) {
	            return false;
	        }
	        i = i/10;
	        prevDigital = currDigital;
	    }
	    return true;
	}

}
