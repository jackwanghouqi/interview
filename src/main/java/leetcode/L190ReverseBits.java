package leetcode;

public class L190ReverseBits {

	public static void main(String[] args) {
		int n = 11;
		System.out.println(Integer.toBinaryString(n));
        int reverseBits = reverseBits(n);
		
		System.out.println(Integer.toBinaryString(reverseBits));
		System.out.println("Result : "+reverseBits);
	}
	
	public static int reverseBits(int n)
    {
        int rev = 0;
 
        // traversing bits of 'n' 
        // from the right
        while (n > 0) 
        {
            // bitwise left shift 
            // 'rev' by 1
            rev <<= 1;
 
            // if current bit is '1'
            if ((int)(n & 1) == 1)
                rev ^= 1;
 
            // bitwise right shift 
            //'n' by 1
            n >>= 1;
            System.out.println("n==>"+Integer.toBinaryString(n));
            System.out.println("rev==>"+Integer.toBinaryString(rev));
        }
        // required number
        return rev;
    }

}
