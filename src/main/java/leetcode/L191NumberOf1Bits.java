package leetcode;

/**
 * Write a function that takes an unsigned integer and returns the number of 1 bits it has.
 * Example:
 * The 32-bit integer 11 has binary representation
 * 00000000000000000000000000001011
 * so the function should return 3.
 * 
 * */
public class L191NumberOf1Bits {

	public static void main(String[] args) {
		printBothFormat(11);
		printBothFormat(11>>>1);
		printBothFormat(11>>1);
		printBothFormat(-11);
		printBothFormat(-11>>>1);
		printBothFormat(-11>>1);
		printBothFormat(0x55555555);
		printBothFormat(0x33333333);
		printBothFormat(0x0f0f0f0f);
		
	}
	
	private static void printBothFormat(int x) {
		System.out.println(Integer.toBinaryString(x) +"===>"+x);
	}
	
	/**
	 * Bit Manipulation Trick
	 * 
	 * n & (n-1) 会去掉最后一个 bit 1.
	 * count +=1
	 * 1101 & 1100 = 1100 !=0 ==> continue with 1100
	 * count +=1
	 * 1100 & 1011 = 1000 !=0 ==> continue with 1000
	 * count +=1
	 * 1000 & 0111 = 0 !=0 ==> end
	 * 
	 * count = 3;
	 * 
	 * O(1)O(1)
	 * */
	public static int hammingWeight2(int n) {
	    int sum = 0;
	    while(n !=0) {
	    	sum++;
	    	n &= (n-1);
	    }
	    return sum;
	}
	
	/**
	 * Loop and Flip
	 * 
	 * 1101 & 1    != 0 ==> count +=1
	 * 1101 & 10   == 0 ==> count +=0
	 * 1101 & 100  != 0 ==> count +=1
	 * 1101 & 1000 != 0 ==> count +=1
	 * 
	 * count = 3;
	 * 
	 * O(1)O(1)
	 * */
	public static int hammingWeight1(int n) {
	    int bits = 0;
	    int mask = 1;
	    for (int i = 0; i < 32; i++) {
	        if ((n & mask) != 0) {
	            bits++;
	        }
	        mask <<= 1;
	    }
	    return bits;
	}
	
	/*
	 * JDK api
	 * */
	public static int hammingWeightJDK(int n) {
	    return Integer.bitCount(n);
	}

}
