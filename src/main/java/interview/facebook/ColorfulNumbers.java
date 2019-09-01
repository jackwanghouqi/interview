package interview.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * For Given Number N find if its COLORFUL number or not
 * 
 * A number can be broken into different contiguous sub-subsequence parts. 
 * Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245. 
 * And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different
 * 
 * */


public class ColorfulNumbers {
	public static void main(String args[]) {
		String number = String.valueOf(12);
		int colorfulNumber = isColorfulNumber(number);
		System.out.println(colorfulNumber);

	}
	
	//TODO 不理解啊 ！！
	private static int isColorfulNumber(String number) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= number.length(); i++) {//split element count
			for (int j = 0; j + i <= number.length(); j++) {//split start index. max is length as k loop from 0 and should only loop once for i=1
				int k = j;
				int prd = 1;

				while (k < j + i) {
					prd = prd * (number.charAt(k) - '0');
					k++;
				}

				if (set.contains(prd)) {
					return 0;
				} else {
					set.add(prd);
				}
			}
		}
		return 1;
	}
}