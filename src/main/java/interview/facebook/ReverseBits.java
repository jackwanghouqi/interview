package interview.facebook;

/**
 * Reverse bits of an 32 bit unsigned integer
 * */

public class ReverseBits {

	public static void main(String[] args) {
		long n = 3;
		System.out.println(Long.toBinaryString(n));
        long reverseBits = reverse(n);
		
		System.out.println(Long.toBinaryString(reverseBits));
		System.out.println("Result : "+reverseBits);
	}
	
	public static long reverse(long a) {
	    long rev = 0;
	    int count = 32;
	    
	    while(a>0) {
	        rev <<=1;
	        if((long)(a&1)==1) rev ^= 1;
	         a >>= 1;
	         count--;
	    }
	    if(count>0)
	        rev <<= count;
	    return rev;
	}

}
