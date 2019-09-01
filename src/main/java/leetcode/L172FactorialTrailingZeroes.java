package leetcode;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * */

public class L172FactorialTrailingZeroes {

	public static void main(String[] args) {
		System.out.println(trailingZeroes(100));
		System.out.println(-1325%10);
	}
	
	public static int trailingZeroes(int n) {
		int count = 0;
		for(int i = 5; n/i >=1; i *=5) {
			count += n/i;
		}
		return count;
	}

}
