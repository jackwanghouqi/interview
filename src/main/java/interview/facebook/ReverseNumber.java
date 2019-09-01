package interview.facebook;

public class ReverseNumber {

	public static void main(String[] args) {
		System.out.println(reverse(-1234567891));

	}
	
	public static int reverse(int a) {
	    long al = Math.abs((long)a);
	    
	    long rev = 0;
	    while(al>0) {
	        rev = rev*10 + al%10;
	        al /= 10;
	    }
	    
	    if(rev<(-1*Math.pow(2,31)) || rev>(Math.pow(2,31)-1)) return 0;
	    if(a >= 0)
	        return (int)rev;
	    else 
	        return -(int)rev;
	}

}
